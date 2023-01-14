package com.Covid_19Patient_Management.Thesis.repository;

import com.Covid_19Patient_Management.Thesis.models.Appointment;
import com.Covid_19Patient_Management.Thesis.models.TreatmentDuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TreatmentDurationRepository extends JpaRepository<TreatmentDuration, Long> {
    Optional<TreatmentDuration> findById(Long id);
    List<TreatmentDuration> findAll();
    @Query(value = "SELECT * FROM treatmentduration WHERE patient_id = :patient_id", nativeQuery = true)
    List<TreatmentDuration> findByPatientID(@Param("patient_id") Long patient_id, Pageable p);
    @Modifying
    @Query(value = "INSERT INTO treatmentduration(doctor_id, patient_id, start_date)" +
            "VALUES(:doctor_id, :patient_id, :start_date)", nativeQuery = true)
    @Transactional
    void registerDoctor(
            @Param("doctor_id") Long doctor_id,
            @Param("patient_id") Long patient_id,
            @Param("start_date") Date start_date
    );
    @Modifying
    @Query(value = "Update treatmentduration set end_date = :end_date where patient_id = :patient_id AND end_date IS :null_end_date", nativeQuery = true)
    @Transactional
    void unRegisterDoctor(
            @Param("end_date") Date end_date,
            @Param("patient_id") Long patient_id,
            @Param("null_end_date") Date null_end_date
    );
}
