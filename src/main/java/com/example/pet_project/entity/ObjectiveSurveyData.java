package com.example.pet_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "objective_survey_data")
public class ObjectiveSurveyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_objective_survey_data")
    private int id;

    @OneToOne(mappedBy = "idObjectiveSurveyData", cascade = CascadeType.REFRESH)
    private Patient patient;

    @Column(name = "conscience")
    private String conscience;

    @Column(name = "health")
    private String health;

    @Column(name = "face")
    private String face;

    @Column(name = "skin_covers")
    private String skinCovers;

    @Column(name = "open_mouth")
    private String openMouth;

}
