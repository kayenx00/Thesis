package com.Covid_19Patient_Management.Thesis.repository;

//import com.Covid_19Patient_Management.Thesis.dtos.HealthInfoDtoForDoctor;
import com.Covid_19Patient_Management.Thesis.models.HealthInformation;
import org.springframework.data.domain.Pageable;
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
    @Query(value = "INSERT INTO HealthInformation(patient_id, blood_pressure, oxygen_level, fever, headache, muscleache, other_diagnose, measured_by, last_update) " +
            "values(:patient_id, :blood_pressure, :oxygen_level, :fever, :headache, :muscleache, :other_diagnose, :measured_by, :last_update)", nativeQuery = true)
    @Transactional
    void healthDeclaration(@Param("patient_id") Long patient_id,
                           @Param("blood_pressure") int blood_pressure,
                           @Param("oxygen_level") int oxygen_level,
                           @Param("fever") String fever,
                           @Param("headache") String headache,
                           @Param("muscleache") String muscleache,
                           @Param("other_diagnose") String other_diagnose,
                           @Param("measured_by") String measured_by,
                           @Param("last_update") Date last_update);
    @Modifying
    @Query(value = "INSERT INTO HealthInformation(patient_id, blood_pressure, oxygen_level, fever, headache, muscleache, other_diagnose, measured_by, comment_from_nurse, last_update) " +
            "values(:patient_id, :blood_pressure, :oxygen_level, :fever, :headache, :muscleache, :other_diagnose, :measured_by, :comment_from_nurse, :last_update)", nativeQuery = true)
    @Transactional
    void healthDeclarationByNurse(
                            @Param("patient_id") Long patient_id,
                           @Param("blood_pressure") int blood_pressure,
                           @Param("oxygen_level") int oxygen_level,
                           @Param("fever") String fever,
                           @Param("headache") String headache,
                           @Param("muscleache") String muscleache,
                           @Param("other_diagnose") String other_diagnose,
                           @Param("measured_by") String measured_by,
                           @Param("comment_from_nurse") String comment_from_nurse,
                           @Param("last_update") Date last_update);
    @Query(value = "SELECT h FROM HealthInformation h WHERE patient_id = :patient_id")
    List<HealthInformation> viewAllHealthDeclarationOfPatientId(@Param("patient_id") Long patient_id, Pageable p);

//    @Query(value = "SELECT h.id AS id, h.patient_id AS patient_id, p.name AS patient_name, p.district AS district, p.city AS city, h.blood_pressure AS blood_pressure, " +
//            "h.oxygen_level AS oxygen_level, h.other_diagnose AS other_diagnose, h.last_update AS last_update, h.advice AS advice " +
//            "from patient p, healthinformation h where h.patient_id = :patient_id " +
//            "Order by patient_id DESC, patient_name DESC, last_update ASC", nativeQuery = true)
////    List<Object> viewPatientDeclarations(@Param("id") Long id, @Param("patient_id") Long patient_id);
//    ArrayList<HealthInfoDtoForDoctor> viewPatientDeclarations(@Param("patient_id") Long patient_id);
    @Modifying
    @Query(value = "Update healthinformation set blood_pressure = :blood_pressure, " +
            "oxygen_level = :oxygen_level, other_diagnose = :other_diagnose, last_update = :last_update where id = :id", nativeQuery = true)
    @Transactional
    void updateHealthDeclaration(@Param("blood_pressure") int blood_pressure,
                                 @Param("oxygen_level") int oxygen_level,
                                 @Param("other_diagnose") String other_diagnose,
                                 @Param("last_update") Date last_update,
                                 @Param("id") Long id);

    @Modifying
    @Query(value = "Update healthinformation set advice = :advice where id = :id", nativeQuery = true)
    @Transactional
    void updateAdvice(@Param("advice")String advice, @Param("id") Long id);
}

