package com.example.pet_project.repository;

import com.example.pet_project.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PatientRep extends JpaRepository<Patient, Integer> {

}
