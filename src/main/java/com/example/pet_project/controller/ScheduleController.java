package com.example.pet_project.controller;

import com.example.pet_project.entity.Doctor;
import com.example.pet_project.entity.Patient;
import com.example.pet_project.entity.Schedule;
import com.example.pet_project.repository.DoctorRep;
import com.example.pet_project.repository.PatientRep;
import com.example.pet_project.repository.ScheduleRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private DoctorRep doctorRep;
    @Autowired
    private PatientRep patientRep;
    @Autowired
    private ScheduleRep scheduleRep;

    @GetMapping("/create")
    public String createSchedule(Model model) {
        Schedule schedule = new Schedule();
        List<Doctor> doctorlist = new ArrayList<>();
        for (Doctor doctor : doctorRep.findAll()) {
            doctorlist.add(doctor);
        }
        List<Patient> patientList = new ArrayList<>();
        for (Patient patient : patientRep.findAll()) {
            patientList.add(patient);
        }

        model.addAttribute("doctors", doctorlist);
        model.addAttribute("schedule", schedule);
        model.addAttribute("patients", patientList);
        return "schedule-create";
    }

    @PostMapping("/created")
    public String createdSchedule(@ModelAttribute Schedule scheduleView) {
        Schedule schedule = new Schedule();
        schedule.setDoctorId(scheduleView.getDoctorId());
        schedule.setPatientId(scheduleView.getPatientId());
        schedule.setDateTimeOfReceipt(scheduleView.getDateTimeOfReceipt());
        scheduleRep.save(schedule);


        return "redirect:/index";
    }
}
