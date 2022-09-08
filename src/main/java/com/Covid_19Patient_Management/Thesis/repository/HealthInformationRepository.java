package com.Covid_19Patient_Management.Thesis.repository;

import com.Covid_19Patient_Management.Thesis.dtos.HealthInfoDtoForDoctor;
import com.Covid_19Patient_Management.Thesis.models.HealthInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public interface HealthInformationRepository extends JpaRepository<HealthInformation, Long> {

    Optional<HealthInformation> findById(Long id);
    List<HealthInformation> findAll();
    @Modifying
    @Query(value = "INSERT INTO HealthInformation(patient_id, blood_pressure, oxygen_level, other_diagnose, last_update) " +
            "values(:patient_id, :blood_pressure, :oxygen_level, :other_diagnose, :last_update)", nativeQuery = true)
    @Transactional
    void healthDeclaration(@Param("patient_id") Long patient_id,
                           @Param("blood_pressure") int blood_pressure,
                           @Param("oxygen_level") int oxygen_level,
                           @Param("other_diagnose") String other_diagnose,
                           @Param("last_update") Date last_update);

    @Query(value = "SELECT h FROM HealthInformation h WHERE patient_id = :patient_id")
    List<HealthInformation> viewAllHealthDeclarationOfPatientId(@Param("patient_id") Long patient_id);

    @Query(value = "SELECT h.id AS id, h.patient_id AS patient_id, p.name AS patient_name, p.district AS district, p.city AS city, h.blood_pressure AS blood_pressure, " +
            "h.oxygen_level AS oxygen_level, h.other_diagnose AS other_diagnose, h.last_update AS last_update " +
            "from patient p, healthinformation h where h.patient_id = :patient_id and p.chosen_doctor = :id " +
            "Order by patient_id DESC, patient_name DESC, last_update ASC", nativeQuery = true)
//    List<Object> viewPatientDeclarations(@Param("id") Long id, @Param("patient_id") Long patient_id);
    ArrayList<HealthInfoDtoForDoctor> viewPatientDeclarations(@Param("id") Long id, @Param("patient_id") Long patient_id);
    @Modifying
    @Query(value = "Update healthinformation set blood_pressure = :blood_pressure, " +
            "oxygen_level = :oxygen_level, other_diagnose = :other_diagnose, last_update = :last_update where id = :id", nativeQuery = true)
    @Transactional
    void updateHealthDeclaration(@Param("blood_pressure") int blood_pressure,
                                 @Param("oxygen_level") int oxygen_level,
                                 @Param("other_diagnose") String other_diagnose,
                                 @Param("last_update") Date last_update,
                                 @Param("id") Long id);
}

