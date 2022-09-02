package com.example.pet_project.controller;

import com.example.pet_project.entity.*;
import com.example.pet_project.repository.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Controller
public class MainController {

    @Autowired
    private PatientRep patientRep;

    @Autowired
    private DoctorRep doctorRep;

    @Autowired
    private ScheduleRep scheduleRep;

    @RequestMapping("/index")
    public String showSchedule(Model model) {
        //выборка по текущей дате
        LocalDate currentDate = LocalDate.now();
        List<Schedule> scheduleList = new ArrayList<>();
        for (Schedule schedule : scheduleRep.findAll()) {
            LocalDate scheduleDate = convertDateToLocalDate(schedule.getDateTimeOfReceipt());
            if (currentDate.equals(scheduleDate)) {
                scheduleList.add(schedule);
            }
        }
        //сортировка по возрастанию времени
        Collections.sort(scheduleList, new Comparator<Schedule>() {
            @Override
            public int compare(Schedule o1, Schedule o2) {
                return o1.getDateTimeOfReceipt().compareTo(o2.getDateTimeOfReceipt());
            }
        });

        List<Doctor> doctorList = new ArrayList<>();
        for(Doctor doctor : doctorRep.findAll()){
            doctorList.add(doctor);
        }

        Map<Date, Map<Doctor, Patient>> scheduleMap = new TreeMap<>();
        for (Schedule schedule : scheduleList) {
            if (!scheduleMap.containsKey(schedule.getDateTimeOfReceipt())) {
                scheduleMap.put(schedule.getDateTimeOfReceipt(), new TreeMap<>() {{
                    put(doctorRep.getReferenceById(schedule.getDoctorId()),
                            patientRep.getReferenceById(schedule.getPatientId()));
                }});
            } else {
                Map<Doctor, Patient> map = scheduleMap.get(schedule.getDateTimeOfReceipt());
                map.put(doctorRep.getReferenceById(schedule.getDoctorId()),
                        patientRep.getReferenceById(schedule.getPatientId()));
                scheduleMap.put(schedule.getDateTimeOfReceipt(), map);
            }
        }
        System.out.println(scheduleMap + "scheduleMap");

        for (Map.Entry<Date, Map<Doctor, Patient>> map : scheduleMap.entrySet()) {
                System.out.println(map.getValue().entrySet().iterator().next().getKey());

        }


        model.addAttribute("schedulesMap", scheduleMap);
        model.addAttribute("doctors", doctorRep.findAll());
        return "index";
    }

    public LocalDate convertDateToLocalDate(Date dateTimeOfReceipt) {
        return dateTimeOfReceipt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
