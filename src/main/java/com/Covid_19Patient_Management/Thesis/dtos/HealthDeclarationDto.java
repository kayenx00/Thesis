package com.Covid_19Patient_Management.Thesis.dtos;

import com.Covid_19Patient_Management.Thesis.models.HealthInformation;

import java.util.Date;

public class HealthDeclarationDto {
    private Long id;
    //    private Long patient_id;
    private int blood_pressure;
    private int oxygen_level;
    private String other_diagnose;
    private String fever;
    private String headache;
    private String muscleache;
    private Date last_update;
    private String advice ;

    public HealthDeclarationDto(Long id, Long patient_id, int blood_pressure, int oxygen_level, String other_diagnose, Date last_update, String advice) {
        this.id = id;
//        this.patient_id = patient_id;
        this.blood_pressure = blood_pressure;
        this.oxygen_level = oxygen_level;
        this.other_diagnose = other_diagnose;
        this.last_update = last_update;
        this.advice = advice;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public HealthDeclarationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//
//    public Long getPatient_id() {
//        return patient_id;
//    }
//
//    public void setPatient_id(Long patient_id) {
//        this.patient_id = patient_id;
//    }

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

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
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

    public void clone(HealthInformation healthInformation){
        this.id = healthInformation.getId();
        this.blood_pressure = healthInformation.getBlood_pressure();
        this.oxygen_level = healthInformation.getOxygen_level();
        this.fever = healthInformation.getFever();
        this.headache = healthInformation.getHeadache();
        this.muscleache = healthInformation.getMuscleache();
        this.other_diagnose = healthInformation.getOther_diagnose();
        this.last_update = healthInformation.getLast_update();
        this.advice = healthInformation.getAdvice();
    }
}
