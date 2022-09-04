package com.Covid_19Patient_Management.Thesis.services;

import com.Covid_19Patient_Management.Thesis.dtos.DoctorDto;
import com.Covid_19Patient_Management.Thesis.dtos.PatientDto;

import java.util.List;

public interface PatientService {
    List<PatientDto> findAllPatient();
    List<PatientDto> viewPatientOfDoctor(Long id);
}
