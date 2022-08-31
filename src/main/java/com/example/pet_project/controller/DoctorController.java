package com.example.pet_project.controller;
import com.example.pet_project.entity.Doctor;
import com.example.pet_project.repository.DoctorRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    DoctorRep doctorRep;

    @GetMapping("/add")
    public String addDoctor(Model model) {
        Doctor doctor = new Doctor();
        model.addAttribute("doctors", doctor);
        return "add-doctor";
    }

    @PostMapping("/added")
    public String addedDoctor(@ModelAttribute Doctor doctor){
        doctorRep.save(doctor);
        return "redirect:/doctor/";
    }

    @GetMapping("/")
    public String showDoctor(Model model) {
        model.addAttribute("doctors", doctorRep.findAll());
        return "doctor-show";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable("id") int id) {
        Doctor doctor = doctorRep.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        doctorRep.delete(doctor);
        return "redirect:/doctor/";
    }
}

