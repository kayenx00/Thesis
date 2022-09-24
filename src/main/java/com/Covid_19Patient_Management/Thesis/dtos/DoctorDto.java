package com.Covid_19Patient_Management.Thesis.dtos;

import com.Covid_19Patient_Management.Thesis.models.Doctor;
import com.Covid_19Patient_Management.Thesis.models.Patient;

import java.util.Collection;

public class DoctorDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Long user_id;
    private String username;
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
    //    public Collection<Patient> getPatients() {
//        return patients;
//    }
//
//    public void setPatients(Collection<Patient> patients) {
//        this.patients = patients;
//    }

    public DoctorDto(){

    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public DoctorDto(Long id, String name, String phone, String username, Long user_id) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.user_id = user_id;
        this.username = username;
//        this.patients = patients;
    }
    public void clone(Doctor doctor){
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.phone = doctor.getPhone();
        this.user_id = doctor.getUser().getId();
        this.username = doctor.getUser().getUsername();
        this.email = doctor.getUser().getEmail();
//        this.patients = doctor.getPatients();
    }
    public void cloneForPatientView(Doctor doctor){
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.phone = doctor.getPhone();
//        this.user_id = doctor.getUser().getId();
        this.username = doctor.getUser().getUsername();
        this.email = doctor.getUser().getEmail();

//        this.patients = doctor.getPatients();
    }

}
