package com.Covid_19Patient_Management.Thesis.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Doctor",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "user_id")
        })
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    @Column(name = "work_place")
    private String workPlace;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Collection<Patient> patients ;

    @JsonManagedReference
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Collection<Nurse> nurses ;

    @JsonManagedReference
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Collection<Appointment> appointments;
    public Doctor(){
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Collection<Patient> patients) {
        this.patients = patients;
    }

    public Collection<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(Collection<Nurse> nurses) {
        this.nurses = nurses;
    }

    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Collection<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }
}
