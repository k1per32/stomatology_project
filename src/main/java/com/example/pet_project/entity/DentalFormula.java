package com.example.pet_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dental_formula")
public class DentalFormula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dental_formula")
    private int idDentalFormula;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_dental_formula")
    private Patient dentalFormulaPatient;

    @Column(name = "tooth")
    private String tooth;

    @Column(name = "problems")
    private String problems;
}
