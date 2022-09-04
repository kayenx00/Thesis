package com.Covid_19Patient_Management.Thesis.services.serviceImp;

import com.Covid_19Patient_Management.Thesis.dtos.DoctorDto;
import com.Covid_19Patient_Management.Thesis.dtos.PatientDto;
import com.Covid_19Patient_Management.Thesis.models.Doctor;
import com.Covid_19Patient_Management.Thesis.models.Patient;
import com.Covid_19Patient_Management.Thesis.repository.PatientRepository;
import com.Covid_19Patient_Management.Thesis.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PatientServiceImplementation implements PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Override
    public List<PatientDto> findAllPatient() {
        List<Patient> patients = patientRepository.findAllPatient();
        List<PatientDto> patientDtos = new ArrayList<>();
        for (Patient p: patients) {
            PatientDto patientDto = new PatientDto();
            patientDto.clone(p);
            patientDtos.add(patientDto);
        }

        return patientDtos;
    }

    @Override
    public List<PatientDto> viewPatientOfDoctor(Long id) {
        List<Patient> patients = patientRepository.viewAllPatientOfDoctorId(id);
        List<PatientDto> patientDtos = new ArrayList<>();
        for (Patient p: patients) {
            PatientDto patientDto = new PatientDto();
            patientDto.clone(p);
            patientDtos.add(patientDto);
        }

        return patientDtos;
    }
}
