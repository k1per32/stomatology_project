package com.example.pet_project.repository;

import com.example.pet_project.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRep extends JpaRepository<Doctor, Integer> {
}
