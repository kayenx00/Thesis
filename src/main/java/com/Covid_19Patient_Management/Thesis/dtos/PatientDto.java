package com.Covid_19Patient_Management.Thesis.dtos;

import com.Covid_19Patient_Management.Thesis.models.Patient;

public class PatientDto {
    private Long id;
    private String name;
    private String id_num;
    private String phone;
    private String city;
    private String district;
    private Long user_id;
    private String username;
    private String chosen_doctor;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PatientDto(){
    }

    public PatientDto(Long id, String name, String id_num, String phone, String city, String district, Long user_id, String username) {
        this.id = id;
        this.name = name;
        this.id_num = id_num;
        this.phone = phone;
        this.city = city;
        this.district = district;
        this.user_id = user_id;
        this.username = username;
    }

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

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getChosen_doctor() {
        return chosen_doctor;
    }

    public void setChosen_doctor(String chosen_doctor) {
        this.chosen_doctor = chosen_doctor;
    }

    public void clone(Patient patient){
        this.id = patient.getId();
        this.name = patient.getName();
        this.id_num = patient.getId_num();
        this.phone = patient.getPhone();
        this.city = patient.getCity();
        this.district = patient.getDistrict();
        this.user_id = patient.getUser().getId();
        this.username = patient.getUser().getUsername();
        if(patient.getDoctor() == null){
            this.chosen_doctor = null;
        }else{
        this.chosen_doctor = patient.getDoctor().getName();}
    }

}
