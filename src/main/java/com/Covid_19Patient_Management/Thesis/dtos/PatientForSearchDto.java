package com.Covid_19Patient_Management.Thesis.dtos;

import com.Covid_19Patient_Management.Thesis.models.Patient;
import com.Covid_19Patient_Management.Thesis.models.TreatmentDuration;

import java.util.Date;

public class PatientForSearchDto {
    private Long id;
    private Long patient_id;
    private String name;
    private String id_num;
    private String chosenDoctor;
    private Date startDate;
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getChosenDoctor() {
        return chosenDoctor;
    }

    public void setChosenDoctor(String chosenDoctor) {
        this.chosenDoctor = chosenDoctor;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public PatientForSearchDto(Long id, Long patient_id, String name, String id_num, String chosenDoctor, Date startDate, Date endDate) {
        this.id = id;
        this.patient_id = patient_id;
        this.name = name;
        this.id_num = id_num;
        this.chosenDoctor = chosenDoctor;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
