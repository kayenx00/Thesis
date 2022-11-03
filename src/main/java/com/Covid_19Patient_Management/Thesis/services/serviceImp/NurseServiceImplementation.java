package com.Covid_19Patient_Management.Thesis.services.serviceImp;

import com.Covid_19Patient_Management.Thesis.dtos.DoctorDto;
import com.Covid_19Patient_Management.Thesis.dtos.NurseDto;
import com.Covid_19Patient_Management.Thesis.models.Doctor;
import com.Covid_19Patient_Management.Thesis.models.Nurse;
import com.Covid_19Patient_Management.Thesis.repository.NurseRepository;
import com.Covid_19Patient_Management.Thesis.services.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class NurseServiceImplementation implements NurseService {
    @Autowired
    private NurseRepository nurseRepository;
    @Override
    public List<NurseDto> findAllNurse() {
        List<Nurse> nurses = nurseRepository.findAllNurse();
        List<NurseDto> nurseDtos = new ArrayList<>();
        for (Nurse n: nurses) {
            NurseDto nurseDto = new NurseDto();
            nurseDto.clone(n);
            nurseDtos.add(nurseDto);
        }
        return nurseDtos;
    }

    @Override
    public String save(Nurse nurse) {
        nurseRepository.save(nurse);
        return nurse.getName();
    }

    @Override
    public NurseDto findById(Long id){
        NurseDto nurseDto = new NurseDto();
        Optional<Nurse> nurse = nurseRepository.findById(id);
        if (nurse.isPresent()) {
            nurseDto.clone(nurse.get());
            return nurseDto;
        } else {
            return null;
        }
    }

    @Override
    public List<NurseDto> findNurseOfDoctor(Long doctor_id) {
        List<Nurse> nurses = nurseRepository.findNurseOfDoctor(doctor_id);
        List<NurseDto> nurseDtos = new ArrayList<>();
        for (Nurse n: nurses) {
            NurseDto nurseDto = new NurseDto();
            nurseDto.clone(n);
            nurseDtos.add(nurseDto);
        }
        return nurseDtos;
    }

    @Override
    public List<NurseDto> findUnassignedNurse(Long doctor_id) {
        List<Nurse> nurses = nurseRepository.findUnassignedOfDoctor(doctor_id);
        List<NurseDto> nurseDtos = new ArrayList<>();
        for (Nurse n: nurses) {
            NurseDto nurseDto = new NurseDto();
            nurseDto.clone(n);
            nurseDtos.add(nurseDto);
        }
        return nurseDtos;    }
}
