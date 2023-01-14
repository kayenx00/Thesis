package com.Covid_19Patient_Management.Thesis.services;

import com.Covid_19Patient_Management.Thesis.dtos.DoctorDto;
import com.Covid_19Patient_Management.Thesis.dtos.PatientDto;
import com.Covid_19Patient_Management.Thesis.dtos.PatientForSearchDto;

import java.util.List;

public interface PatientService {
    List<PatientDto> findAllPatient();
    List<PatientDto> viewPatientOfDoctor(Long id);
    List<PatientForSearchDto> viewSearchedPatient(Long id, String id_num, String name);
}
