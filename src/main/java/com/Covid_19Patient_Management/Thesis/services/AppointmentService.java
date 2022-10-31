package com.Covid_19Patient_Management.Thesis.services;

import com.Covid_19Patient_Management.Thesis.dtos.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    List<AppointmentDto> findAllRequestAppointment(String type, boolean is_confirmed, Long patient_id);
    List<AppointmentDto> findAllInitiateAppointment(String type, boolean is_confirmed, Long doctor_id);
    List<AppointmentDto> patientViewUpcomingAppointment(Long patient_id, boolean is_confirmed);
    List<AppointmentDto> doctorViewUpcomingAppointment(Long doctor_id, boolean is_confirmed);
    String rejectAnAppointment(Long id);
}
