package com.example.pet_project.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "doctors")
public class Doctor implements Comparable<Doctor> {


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

    @Override
    public String toString() {
        return surname + " " + name + " " + patronymic + " ";
    }


    @Override
    public int compareTo(Doctor o) {
        return o.getSurname().compareTo(this.surname);
    }
}

