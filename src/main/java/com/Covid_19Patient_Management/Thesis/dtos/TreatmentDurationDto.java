package com.Covid_19Patient_Management.Thesis.dtos;

import com.Covid_19Patient_Management.Thesis.models.TreatmentDuration;

import java.util.Date;

public class TreatmentDurationDto {
    private Long id;
    private String patient_name;
    private String doctor_name;
    private Date start_date;
    private Date end_date;

    public TreatmentDurationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
    public void clone(TreatmentDuration treatmentDuration){
        this.id = treatmentDuration.getId();
        if(treatmentDuration.getDoctor() == null){
            this.doctor_name = null;
        } else {
            this.doctor_name = treatmentDuration.getDoctor().getName();
        }
        if(treatmentDuration.getPatient() == null){
            this.patient_name = null;
        } else {
            this.patient_name = treatmentDuration.getPatient().getName();
        }
        this.start_date = treatmentDuration.getStart_date();
        if(treatmentDuration.getEnd_date() == null){
            this.end_date = null;
        } else {
            this.end_date = treatmentDuration.getEnd_date();
        }
    }
}
