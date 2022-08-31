package com.example.pet_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "mkb_codes")
public class MkbCodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mkb_code")
    private int id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id_mkb_code")
    private Patient foreignKeyCodes;

    @Column(name = "prelim_diagnosis_code")
    private int prelimDiagnosisCode;


    @Column(name = "clinical_diagnosis_code")
    private int clinicalDiagnosisCode;


}
