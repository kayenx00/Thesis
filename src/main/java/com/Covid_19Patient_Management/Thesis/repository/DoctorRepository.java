package com.Covid_19Patient_Management.Thesis.repository;

import com.Covid_19Patient_Management.Thesis.models.Doctor;
import com.Covid_19Patient_Management.Thesis.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findById(Long id);
    List<Doctor>  findAll();
    @Query(value = "SELECT * FROM DOCTOR", nativeQuery = true)
    List<Doctor> findAllDoctor();
    @Modifying
    @Query(value = "INSERT INTO Doctor(name, phone, user_id) VALUES(:name, :phone, :id)", nativeQuery = true)
    @Transactional
    void createDoctor(@Param("name")String name, @Param("phone") String phone, @Param("id") Long id);

    @Modifying
    @Query(value = "Update Doctor set name = :name, phone = :phone where id = :id", nativeQuery = true)
    @Transactional
    void alterDoctor(@Param("name")String name, @Param("phone") String phone, @Param("id") Long id);
    @Query(value = "SELECT * From doctor where user_id = :user_id", nativeQuery = true)
    Optional<Doctor> findDoctorByUserID(@Param("user_id") Long id);
    @Query(value = "SELECT h.id, h.patient_id, h.patient_name, p.district, p.city, h.Blood_Pressure, h.Oxygen_level, h.Other_Diagnose, h.lastUpdate " +
            "from patient p, healthinformation h where h.patient_id = p.id and p.chosen_doctor = :id " +
            "Order by h.patient_id DESC, p.name DESC, h.lastUpdate ASC", nativeQuery = true)
    List<Object> viewPatientDeclarations(@Param("id") Long id);

}
