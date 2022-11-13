package com.Covid_19Patient_Management.Thesis.services.serviceImp;

import com.Covid_19Patient_Management.Thesis.dtos.DoctorDto;
import com.Covid_19Patient_Management.Thesis.dtos.HealthDeclarationDto;
import com.Covid_19Patient_Management.Thesis.dtos.PatientDto;
import com.Covid_19Patient_Management.Thesis.models.Doctor;
import com.Covid_19Patient_Management.Thesis.models.HealthInformation;
import com.Covid_19Patient_Management.Thesis.models.Patient;
import com.Covid_19Patient_Management.Thesis.repository.HealthInformationRepository;
import com.Covid_19Patient_Management.Thesis.services.HealthInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HealthInformationServiceImplementation implements HealthInformationService {
    @Autowired
    private HealthInformationRepository healthInformationRepository;
    @Override
    public void addHealthDeclaration() {

    }

    @Override
    public List<HealthDeclarationDto> patientFindAllDeclaration() {
        {
            List<HealthInformation> healthInformations = healthInformationRepository.findAll();
            List<HealthDeclarationDto> healthDeclarationDtos = new ArrayList<>();
            for (HealthInformation h: healthInformations) {
                HealthDeclarationDto doctorDto = new HealthDeclarationDto();
                doctorDto.clone(h);
                healthDeclarationDtos.add(doctorDto);
            }
            return healthDeclarationDtos;
        }
    }

    @Override
    public List<HealthDeclarationDto> listAllHealthInformationDtoOfPatient(Long id) {
        {
            List<HealthInformation> list = healthInformationRepository.viewAllHealthDeclarationOfPatientId(id, PageRequest.of(0, 1000, Sort.Direction.DESC, "last_update"));
            List<HealthDeclarationDto> DtoList = new ArrayList<>();
            for (HealthInformation h: list) {
                HealthDeclarationDto healthDeclarationDto = new HealthDeclarationDto();
                healthDeclarationDto.clone(h);
                DtoList.add(healthDeclarationDto);
            }

            return DtoList;
        }
    }
}
