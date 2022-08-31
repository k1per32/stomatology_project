package com.example.pet_project.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule")
    private int id;

    @Column(name = "id_patient")
    private int patientId;

    @Column(name = "id_doctor")
    private int doctorId;

    @Column(name = "date_time_of_receipt")
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private Date dateTimeOfReceipt;


}
