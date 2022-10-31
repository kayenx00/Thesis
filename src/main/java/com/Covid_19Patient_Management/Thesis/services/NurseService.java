package com.Covid_19Patient_Management.Thesis.services;

import com.Covid_19Patient_Management.Thesis.dtos.NurseDto;
import com.Covid_19Patient_Management.Thesis.models.Nurse;

import java.io.IOException;
import java.util.List;

public interface NurseService {
    List<NurseDto> findAllNurse();
    //List<NurseDto> patientFindAllDoctor();
    //    DoctorDto findById(String id);
//    List<DoctorDto> findByName(String name);
   // String delete(Doctor doctor) throws IOException;
    //    Doctor edit(Doctor doctor);
    String save(Nurse nurse);
    NurseDto findById(Long id);
}
