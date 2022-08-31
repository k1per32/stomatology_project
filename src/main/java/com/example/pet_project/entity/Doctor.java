package com.example.pet_project.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "doctors")
public class Doctor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;//отчество

    @ManyToMany(mappedBy = "doctorList", fetch = FetchType.EAGER)
    List<Patient> patientList;

    public Doctor(int id, String name, String surname, String patronymic, List<Patient> patientList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.patientList = patientList;
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + patronymic + " ";
    }
}

