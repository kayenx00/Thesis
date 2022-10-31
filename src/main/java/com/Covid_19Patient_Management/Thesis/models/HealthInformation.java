package com.Covid_19Patient_Management.Thesis.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "healthinformation")
public class HealthInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @Column(name = "blood_pressure")
    private int blood_pressure;
    @Column(name = "oxygen_level")
    private int oxygen_level;
    @Column(name = "fever")
    private String fever ;
    @Column(name = "headache")
    private String headache ;
    @Column(name = "muscleache")
    private String muscleache ;
    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "other_diagnose")

    private String other_diagnose;
    @Column(name = "last_update")
    private Date last_update;
    @Column(name = "advice")
    private String advice;
    @Size(min = 1, max = 255)
    @Column(name = "comment_from_nurse")
    private String comment;
    @Column(name = "measured_by")
    private String measured_by;
    public HealthInformation() {
    }

    public HealthInformation(Long id, Patient patient, int blood_pressure, int oxygen_level, String fever, String headache, String muscleache, String other_diagnose, Date last_update, String advice) {
        this.id = id;
        this.patient = patient;
        this.blood_pressure = blood_pressure;
        this.oxygen_level = oxygen_level;
        this.fever = fever;
        this.headache = headache;
        this.muscleache = muscleache;
        this.other_diagnose = other_diagnose;
        this.last_update = last_update;
        this.advice = advice;
    }

    public HealthInformation(Long id, Patient patient, int blood_pressure, int oxygen_level, String fever, String headache, String muscleache, String other_diagnose, Date last_update, String advice, String comment, String measured_by) {
        this.id = id;
        this.patient = patient;
        this.blood_pressure = blood_pressure;
        this.oxygen_level = oxygen_level;
        this.fever = fever;
        this.headache = headache;
        this.muscleache = muscleache;
        this.other_diagnose = other_diagnose;
        this.last_update = last_update;
        this.advice = advice;
        this.comment = comment;
        this.measured_by = measured_by;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(int blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public int getOxygen_level() {
        return oxygen_level;
    }

    public void setOxygen_level(int oxygen_level) {
        this.oxygen_level = oxygen_level;
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }

    public String getHeadache() {
        return headache;
    }

    public void setHeadache(String headache) {
        this.headache = headache;
    }

    public String getMuscleache() {
        return muscleache;
    }

    public void setMuscleache(String muscleache) {
        this.muscleache = muscleache;
    }

    public String getOther_diagnose() {
        return other_diagnose;
    }

    public void setOther_diagnose(String other_diagnose) {
        this.other_diagnose = other_diagnose;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMeasured_by() {
        return measured_by;
    }

    public void setMeasured_by(String measured_by) {
        this.measured_by = measured_by;
    }
}
