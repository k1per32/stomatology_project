package com.example.pet_project.controller;

import com.example.pet_project.entity.*;
import com.example.pet_project.repository.*;
import net.bytebuddy.asm.Advice;
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
import java.util.concurrent.ScheduledFuture;

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

        System.out.println(scheduleList + "scheduleList");

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Doctor.class)
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(DentalFormula.class)
                .addAnnotatedClass(MkbCodes.class)
                .addAnnotatedClass(ObjectiveSurveyData.class)
                .addAnnotatedClass(Schedule.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query1 = session.createQuery
                ("FROM Doctor", Doctor.class);
        List<Doctor> doctorList = query1.getResultList();
        System.out.println(doctorList + "doctorList");

        Query query2 = session.createQuery
                ("FROM Patient", Patient.class);
        List<Patient> patientList = query2.getResultList();
        System.out.println(patientList + "patientList");

        Map<Integer, Patient> patientMap = new HashMap<>();
        for (Patient patient : patientList) {
            patientMap.put(patient.getId(), patient);
        }
        System.out.println(patientMap + "patientMap");
        session.close();

        Map<Integer, Schedule> scheduleMap = new HashMap<>();
        for (Schedule schedule : scheduleList) {
            scheduleMap.put(schedule.getId(), schedule);
        }
        System.out.println(scheduleMap + "scheduleMap");




        model.addAttribute("schedulesMap", scheduleMap);//
        model.addAttribute("patientMap", patientMap);//
        model.addAttribute("doctors", doctorList);//
        return "index";
    }

    public LocalDate convertDateToLocalDate(Date dateTimeOfReceipt) {
        return dateTimeOfReceipt.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
