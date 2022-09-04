package com.Covid_19Patient_Management.Thesis.repository;

import com.Covid_19Patient_Management.Thesis.models.Doctor;
import com.Covid_19Patient_Management.Thesis.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findById(Long id);
    List<Patient> findAll();
    @Query(value = "SELECT * FROM PATIENT", nativeQuery = true)
    List<Patient> findAllPatient();
    @Modifying
    @Query(value = "INSERT into PATIENT(user_id) VALUES (:id)", nativeQuery = true)
    @Transactional
    void insertIdUSerToPatient(@Param("id") Long id);
    @Query(value = "SELECT * From patient where user_id = :user_id", nativeQuery = true)
    Optional<Patient> findPatientByUserID(@Param("user_id") Long id);
    @Modifying
    @Query(value = "Update patient set name = :name, phone = :phone , id_num = :id_num, city = :city, district =:district " +
            "where user_id = :id", nativeQuery = true)
    @Transactional
    void updatePatient(@Param("name")String name, @Param("id_num") String id_num, @Param("phone") String phone,
                     @Param("city") String city, @Param("district") String district, @Param("id") Long id);
    @Modifying
    @Query(value = "Update patient set chosen_doctor = :chosen_doctor where id = :id", nativeQuery = true)
    @Transactional
    void registerDoctor(@Param("id")Long id, @Param("chosen_doctor") Long chosen_doctor);

    @Query(value = "SELECT * FROM Patient where chosen_doctor = :id", nativeQuery = true)
    List<Patient> viewAllPatientOfDoctorId(@Param("id") Long id);

}
