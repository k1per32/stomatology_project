package com.example.pet_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;//отчество

    @Column(name = "sex")//пол
    private String sex;

    @Column(name = "date_of_birth")
    @DateTimeFormat
    private String dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "i_n_n")
    private Long iNN;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "serial_number_passport")
    private Long serialNumberPassport;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "access")
    private boolean access;

    @DateTimeFormat
    @Column(name = "date_of_access")
    private String dateOfAccess;

    @Column(name = "complaints")//жалобы
    private String complaints;

    @Column(name = "concomitant_diseases")//перенесенные и сопутствующие заболевания
    private String concomitantDiseases;

    @Column(name = "dev_real_disease")//Развитие настоящего заболевания
    private String devOfRealDisease;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//айди данных объективного обследоования
    @JoinColumn(name = "id_objective_survey_data")
    private ObjectiveSurveyData idObjectiveSurveyData;

    @Column(name = "ex_vestibule_oral_cavity")//Осмотр преддверия и полости рта
    private String exVestibuleOralCavity;

    @Column(name = "condition_lymph_nodes")//Состояние лимфоузлов
    private String conditionLymphNodes;

    @Column(name = "condition_teeth_periodontal")//Состояние зубов и пародонта
    private String conditionTeethPeriodontal;

    @OneToMany(
            mappedBy = "dentalFormulaPatient",
            cascade = CascadeType.ALL
    )
    private List<DentalFormula> dentalFormulaList;

    @Column(name = "occlusion")//прикус
    private String occlusion;

    @Column(name = "hygiene_index")//индекс гигиены
    private int hygieneIndex;

    @Column(name = "prelim_diagnosis")//предварительный диагноз
    private String prelimDiagnosis;

    @Column(name = "survey_plan")//план обследования
    private String surveyPlan;

    @Column(name = "survey_data")//данные обследования
    private String surveyData;

    @Column(name = "clinical_diagnosis")//клинический диагноз
    private String clinicalDiagnosis;

    @Column(name = "treatment_plan")//план лечения
    private String treatmentPlan;

    @Column(name = "treatment_protocol")//протокол лечения
    private String treatmentProtocol;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mkb_code")
    private MkbCodes id_mkb_code;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "schedules"
            , joinColumns = @JoinColumn(name = "id_patient")
            , inverseJoinColumns = @JoinColumn(name = "id_doctor")
    )
    private List<Doctor> doctorList;

    public Patient(int id, String name, String surname, String patronymic, List<Doctor> doctorList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.doctorList = doctorList;
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + patronymic;
    }
}
