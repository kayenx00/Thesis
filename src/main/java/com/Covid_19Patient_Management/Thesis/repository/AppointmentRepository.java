package com.Covid_19Patient_Management.Thesis.repository;

import com.Covid_19Patient_Management.Thesis.models.Appointment;
import com.Covid_19Patient_Management.Thesis.models.Doctor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findById(Long id);
    List<Appointment> findAll();
    @Query(value = "SELECT * FROM Appointment", nativeQuery = true)
    List<Appointment> findAllAppointment();

    @Modifying
    @Query(value = "INSERT INTO Appointment(doctor_id, date, start_time, duration, type, is_confirmed)" +
            "VALUES(:doctor_id, :date, :start_time, :duration, :type, :is_confirmed)", nativeQuery = true)
    @Transactional
    void initiateAnAppointment(
            @Param("doctor_id") Long doctor_id,
            @Param("date") Date date,
            @Param("start_time") String start_time,
            @Param("duration") int duration,
            @Param("type") String type,
            @Param("is_confirmed") boolean is_confirmed
            );
    @Modifying
    @Query(value = "INSERT INTO Appointment(patient_id, doctor_id, date, start_time, duration, type, is_confirmed) VALUES(:patient_id, :doctor_id, :date, :start_time, :duration, :type, :is_confirmed)", nativeQuery = true)
    @Transactional
    void requestAnAppointment(
            @Param("patient_id") Long patient_id,
            @Param("doctor_id") Long doctor_id,
            @Param("date") Date date,
            @Param("start_time") String start_time,
            @Param("duration") int duration,
            @Param("type") String type,
            @Param("is_confirmed") boolean is_confirmed
            );
    @Modifying
    @Query(value = "Update Appointment set is_confirmed = :is_confirmed, doctor_id = :doctor_id " +
            "where id = :id", nativeQuery = true)
    @Transactional
    void confirmAnAppointment(
            @Param("is_confirmed") boolean is_confirmed,
            @Param("doctor_id") Long doctor_id,
            @Param("id") Long id
    );
    @Modifying
    @Query(value = "Update Appointment set is_confirmed = :is_confirmed, patient_id = :patient_id " +
            "where id = :id", nativeQuery = true)
    @Transactional
    void bookAnAppointment(
            @Param("is_confirmed") boolean is_confirmed,
            @Param("patient_id") Long patient_id,
            @Param("id") Long id
    );

    @Query(value = "SELECT * FROM Appointment where type = :type and doctor_id = :doctor_id and is_confirmed = :is_confirmed and not patient_id is :patient_id", nativeQuery = true)
    List<Appointment> viewRequestAppointment(
            @Param("type") String type,
            @Param("doctor_id") Long doctor_id,
            @Param("is_confirmed") boolean is_confirmed,
            @Param("patient_id") Long patient_id,

            Pageable p

    );
    @Query(value = "SELECT * FROM Appointment where type = :type and doctor_id = :doctor_id and patient_id = :patient_id and is_confirmed = :is_confirmed", nativeQuery = true)
    List<Appointment> viewRequestAppointmentForPatient(
            @Param("type") String type,
            @Param("doctor_id") Long doctor_id,
            @Param("patient_id") Long patient_id,
            @Param("is_confirmed") boolean is_confirmed
//            ,Pageable p

    );
    @Query(value = "SELECT * FROM Appointment where type = :type and doctor_id = :doctor_id and is_confirmed = :is_confirmed", nativeQuery = true)
    List<Appointment> viewInitiatedAppointment(
            @Param("type") String type,
            @Param("doctor_id") Long doctor_id,
            @Param("is_confirmed") boolean is_confirmed,
            Pageable p
    );

    @Query(value = "SELECT * FROM Appointment where patient_id = :patient_id and is_confirmed = :is_confirmed", nativeQuery = true)
    List<Appointment> patientUpcomingViewAppointment(
            @Param("patient_id") Long patient_id,
            @Param("is_confirmed") boolean is_confirmed,
            Pageable p
    );

    @Query(value = "SELECT * FROM Appointment where doctor_id = :doctor_id and is_confirmed = :is_confirmed", nativeQuery = true)
    List<Appointment> doctorViewUpcomingAppointment(
            @Param("doctor_id") Long doctor_id,
            @Param("is_confirmed") boolean is_confirmed,
            Pageable p
    );

    @Modifying
    @Query(value = "Update Appointment set is_confirmed = :is_confirmed, patient_id = :patient_id " +
            "where id = :id", nativeQuery = true)
    @Transactional
    void patientCancelAnAppointment(
            @Param("is_confirmed") boolean is_confirmed,
            @Param("patient_id") Long patient_id,
            @Param("id") Long id
    );
    @Modifying
    @Query(value = "Update Appointment set is_confirmed = :is_confirmed " +
            "where id = :id", nativeQuery = true)
    @Transactional
    void doctorCancelAnAppointment(
            @Param("is_confirmed") boolean is_confirmed,
            @Param("id") Long id
    );

}
