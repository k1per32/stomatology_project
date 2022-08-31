package com.example.pet_project.repository;

import com.example.pet_project.entity.DentalFormula;
import com.example.pet_project.entity.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DentalFormulaRep extends CrudRepository<DentalFormula, Integer> {
    public Optional<DentalFormula> findAllByDentalFormulaPatient(Patient patient);
}
