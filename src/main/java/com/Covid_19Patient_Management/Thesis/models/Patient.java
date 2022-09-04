package com.Covid_19Patient_Management.Thesis.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "Patient", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id")})
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String id_num;
    private String phone;
    private String city;
    private String district;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")

    private User user;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "chosen_doctor")
    private Doctor doctor;

    @JsonManagedReference
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Collection<HealthInformation> healthInformation;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<HealthInformation> getHealthInformation() {
        return healthInformation;
    }

    public void setHealthInformation(Collection<HealthInformation> healthInformation) {
        this.healthInformation = healthInformation;
    }
}
