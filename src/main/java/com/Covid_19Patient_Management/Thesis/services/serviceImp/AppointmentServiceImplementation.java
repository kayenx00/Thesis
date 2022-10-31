package com.Covid_19Patient_Management.Thesis.services.serviceImp;

import com.Covid_19Patient_Management.Thesis.dtos.AppointmentDto;
import com.Covid_19Patient_Management.Thesis.models.Appointment;
import com.Covid_19Patient_Management.Thesis.repository.AppointmentRepository;
import com.Covid_19Patient_Management.Thesis.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImplementation implements AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Override
    public List<AppointmentDto> findAllRequestAppointment(String type, boolean is_confirmed, Long doctor_id) {
        List<Appointment> appointments = appointmentRepository.viewRequestAppointment(type, doctor_id, is_confirmed);
        List<AppointmentDto> appointmentDtos = new ArrayList<AppointmentDto>();
        Date currentDate = new Date();
        for(Appointment a : appointments){
            if(currentDate.compareTo(a.getDate()) <= 0){
                AppointmentDto appointmentDto = new AppointmentDto();
                appointmentDto.clone(a);
                appointmentDtos.add(appointmentDto);
            }
        }
        return appointmentDtos;
    }
    @Override
    public List<AppointmentDto> findAllInitiateAppointment(String type, boolean is_confirmed, Long doctor_id) {
        List<Appointment> appointments = appointmentRepository.viewInitiatedAppointment(type, doctor_id, is_confirmed);
        List<AppointmentDto> appointmentDtos = new ArrayList<AppointmentDto>();
        Date currentDate = new Date();
        for(Appointment a : appointments){
            if(currentDate.compareTo(a.getDate()) <= 0){
                AppointmentDto appointmentDto = new AppointmentDto();
                appointmentDto.clone(a);
                appointmentDtos.add(appointmentDto);
            }
        }
        return appointmentDtos;
    }

    @Override
    public List<AppointmentDto> patientViewUpcomingAppointment(Long patient_id, boolean is_confirmed) {
        List<Appointment> appointments = appointmentRepository.patientUpcomingViewAppointment(patient_id, is_confirmed);
        List<AppointmentDto> appointmentDtos = new ArrayList<AppointmentDto>();
        Date currentDate = new Date();
        for(Appointment a : appointments){
            if(currentDate.compareTo(a.getDate()) <= 0){
                AppointmentDto appointmentDto = new AppointmentDto();
                appointmentDto.clone(a);
                appointmentDtos.add(appointmentDto);
            }
        }
        return appointmentDtos;
    }

    @Override
    public List<AppointmentDto> doctorViewUpcomingAppointment(Long doctor_id, boolean is_confirmed) {
        List<Appointment> appointments = appointmentRepository.doctorViewUpcomingAppointment(doctor_id, is_confirmed);
        List<AppointmentDto> appointmentDtos = new ArrayList<AppointmentDto>();
        Date currentDate = new Date();
        for(Appointment a : appointments){
            if(currentDate.compareTo(a.getDate()) <= 0){
                AppointmentDto appointmentDto = new AppointmentDto();
                appointmentDto.clone(a);
                appointmentDtos.add(appointmentDto);
            }
        }
        return appointmentDtos;
    }

    @Override
    public String rejectAnAppointment(Long id) {
        appointmentRepository.deleteById(id);
        return "Success";
    }
}
