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

    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name = "other_diagnose")

    private String other_diagnose;
    @Column(name = "last_update")


    private Date last_update;

    public HealthInformation() {
    }

    public HealthInformation(Long id, Patient patient, int blood_pressure, int oxygen_level, String other_diagnose, Date last_update) {
        this.id = id;
        this.patient = patient;
        this.blood_pressure = blood_pressure;
        this.oxygen_level = oxygen_level;
        this.other_diagnose = other_diagnose;
        this.last_update = last_update;
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
}
