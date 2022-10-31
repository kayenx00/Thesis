package com.Covid_19Patient_Management.Thesis.dtos;

import com.Covid_19Patient_Management.Thesis.models.Appointment;

import java.util.Date;

public class AppointmentDto {
    private Long id;
    private Date date;
    private String start_time;
    private int duration;
    private String type;
    private boolean is_confirmed;
    private String doctor_name;
    private String patient_name;

    public AppointmentDto() {
    }

    public void clone(Appointment appointment){
        this.id = appointment.getId();
        this.date = appointment.getDate();
        this.start_time = appointment.getStart_time();
        this.duration = appointment.getDuration();
        this.type = appointment.getType();
        this.is_confirmed = appointment.isConfirmed();
        if(appointment.getDoctor() == null){
            this.doctor_name = null;
        } else {
            this.doctor_name = appointment.getDoctor().getName();
        }
        if(appointment.getPatient() == null){
            this.patient_name = null;
        } else {
            this.patient_name = appointment.getPatient().getName();
        }

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIs_confirmed() {
        return is_confirmed;
    }

    public void setIs_confirmed(boolean is_confirmed) {
        this.is_confirmed = is_confirmed;
    }

//    public Long getDoctor_id() {
//        return doctor_id;
//    }
//
//    public void setDoctor_id(Long doctor_id) {
//        this.doctor_id = doctor_id;
//    }
//
//    public Long getPatient_id() {
//        return patient_id;
//    }
//
//    public void setPatient_id(Long patient_id) {
//        this.patient_id = patient_id;
//    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }
}
