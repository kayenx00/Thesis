package com.Covid_19Patient_Management.Thesis.dtos;

import com.Covid_19Patient_Management.Thesis.models.Nurse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NurseDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    @JsonProperty("work_place")
    private String work_place;
    private Long user_id;
    private String username;
    private String work_under_doctor;
//    private Collection<Patient> patients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWork_under_doctor() {
        return work_under_doctor;
    }

    public void setWork_under_doctor(String work_under_doctor) {
        this.work_under_doctor = work_under_doctor;
    }
    //    public Collection<Patient> getPatients() {
//        return patients;
//    }
//
//    public void setPatients(Collection<Patient> patients) {
//        this.patients = patients;
//    }

    public NurseDto(){

    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public NurseDto(Long id, String name, String phone, String username, Long user_id) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.user_id = user_id;
        this.username = username;
//        this.patients = patients;
    }
    public void clone(Nurse nurse){
        this.id = nurse.getId();
        this.name = nurse.getName();
        this.phone = nurse.getPhone();
        this.user_id = nurse.getUser().getId();
        this.username = nurse.getUser().getUsername();
        this.email = nurse.getUser().getEmail();
        this.work_place = nurse.getWorkPlace();
        if(nurse.getDoctor() ==null) {
            this.work_under_doctor = null;
        } else {
            this.work_under_doctor = nurse.getDoctor().getName();
        }
//        this.patients = doctor.getPatients();
    }
    public void cloneForPatientView(Nurse nurse){
        this.id = nurse.getId();
        this.name = nurse.getName();
        this.phone = nurse.getPhone();
//        this.user_id = doctor.getUser().getId();
        this.username = nurse.getUser().getUsername();
        this.email = nurse.getUser().getEmail();
        this.work_place = nurse.getWorkPlace();

//        this.patients = doctor.getPatients();
    }
}
