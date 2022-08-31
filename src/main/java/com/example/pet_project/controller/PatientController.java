package com.example.pet_project.controller;

import com.example.pet_project.entity.DentalFormula;
import com.example.pet_project.entity.Patient;
import com.example.pet_project.repository.DentalFormulaRep;
import com.example.pet_project.repository.PatientRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRep patientRep;

    @Autowired
    private DentalFormulaRep dentalFormulaRep;

    @GetMapping("/add")
    public String addPatient(Model model) {
        Patient patient = new Patient();
        DentalFormula dentalFormula = new DentalFormula();
        model.addAttribute("dentalFormulaPatient", dentalFormula);
        model.addAttribute("patient", patient);
        return "add-patient";
    }

    @PostMapping("/added")
    public String addedPatient(@ModelAttribute Patient patient, @ModelAttribute DentalFormula dentalFormula) {
        dentalFormula.setDentalFormulaPatient(patient);
        patientRep.save(patient);
        dentalFormulaRep.save(dentalFormula);
        return "redirect:/patient/";
    }

    @GetMapping("/")
    public String showPatient(Model model) {
        model.addAttribute("patients", patientRep.findAll());
        return "patient-show";
    }

    @GetMapping("/update/{id}")
    public String updatePatient(@PathVariable("id") int id, Model model) {
        Patient patient = patientRep.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid user Id: " + id));
        DentalFormula dentalFormula = dentalFormulaRep.findAllByDentalFormulaPatient(patient).orElseThrow(() ->
                new IllegalArgumentException("Invalid user Id: " + id));
        model.addAttribute("patient", patient);
        model.addAttribute("dentalFormulaPatient", dentalFormula);
        return "update-patient";
    }

    @PostMapping("/updated/{id}")
    public String updatedPatient(@ModelAttribute Patient patient, @ModelAttribute DentalFormula dentalFormula) {
        DentalFormula dentalFormula1 = dentalFormulaRep.findAllByDentalFormulaPatient(patient).orElseThrow(() ->
                new IllegalArgumentException("Invalid patient: " + patient));
        dentalFormula1.setProblems(dentalFormula.getProblems());
        dentalFormula1.setTooth(dentalFormula.getTooth());
        patientRep.save(patient);
        dentalFormulaRep.save(dentalFormula1);
        return "redirect:/patient/";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable("id") int id) {
        Patient patient = patientRep.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid user Id: " + id));
        DentalFormula dentalFormula = dentalFormulaRep.findAllByDentalFormulaPatient(patient).orElseThrow(()
                -> new IllegalArgumentException("Invalid user Id: " + id));
        dentalFormulaRep.delete(dentalFormula);
        patientRep.delete(patient);
        return "redirect:/patient/";
    }

}
