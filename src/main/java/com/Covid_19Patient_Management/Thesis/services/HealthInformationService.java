package com.Covid_19Patient_Management.Thesis.services;

import com.Covid_19Patient_Management.Thesis.dtos.DoctorDto;
import com.Covid_19Patient_Management.Thesis.dtos.HealthDeclarationDto;

import java.util.List;

public interface HealthInformationService {
    void addHealthDeclaration();
    List<HealthDeclarationDto> patientFindAllDeclaration();
    List<HealthDeclarationDto> listAllHealthInformationDtoOfPatient(Long id);

}
