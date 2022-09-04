package com.Covid_19Patient_Management.Thesis.repository;

import com.Covid_19Patient_Management.Thesis.models.HealthInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Query(value = "SELECT h.id, h.patient_id, p.name, p.district, p.city, h.blood_pressure, h.oxygen_level, h.other_diagnose, h.last_update " +
            "from patient p, healthinformation h where h.patient_id = :patient_id and p.chosen_doctor = :id " +
            "Order by h.patient_id DESC, p.name DESC, h.last_update ASC", nativeQuery = true)
    List<Object> viewPatientDeclarations(@Param("id") Long id, @Param("patient_id") Long patient_id);
}

