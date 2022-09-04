package com.Covid_19Patient_Management.Thesis.services;

import com.Covid_19Patient_Management.Thesis.dtos.DoctorDto;
import com.Covid_19Patient_Management.Thesis.models.Doctor;

import java.io.IOException;
import java.util.List;

public interface DoctorService {

    List<DoctorDto> findAllDoctor();
    List<DoctorDto> patientFindAllDoctor();
//    DoctorDto findById(String id);
//    List<DoctorDto> findByName(String name);
    String delete(Doctor doctor) throws IOException;
//    Doctor edit(Doctor doctor);
    String save(Doctor doctor);
    DoctorDto findById(Long id);
}
