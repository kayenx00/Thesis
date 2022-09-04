package com.Covid_19Patient_Management.Thesis.services.serviceImp;

import com.Covid_19Patient_Management.Thesis.dtos.DoctorDto;
import com.Covid_19Patient_Management.Thesis.models.Doctor;
import com.Covid_19Patient_Management.Thesis.repository.DoctorRepository;
import com.Covid_19Patient_Management.Thesis.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImplementation implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    private static final Logger logger = LoggerFactory.getLogger(DoctorServiceImplementation.class);

    @Override
    public List<DoctorDto> findAllDoctor() {
        List<Doctor> doctors = doctorRepository.findAllDoctor();
        List<DoctorDto> doctorDtos = new ArrayList<>();
        for (Doctor d: doctors) {
            DoctorDto doctorDto = new DoctorDto();
            doctorDto.clone(d);
            doctorDtos.add(doctorDto);
        }
        return doctorDtos;
    }
    @Override
    public List<DoctorDto> patientFindAllDoctor() {
        List<Doctor> doctors = doctorRepository.findAllDoctor();
        List<DoctorDto> doctorDtos = new ArrayList<>();
        for (Doctor d: doctors) {
            DoctorDto doctorDto = new DoctorDto();
            doctorDto.cloneForPatientView(d);
            doctorDtos.add(doctorDto);
        }
        return doctorDtos;
    }

//    @Override
//    public DoctorDto findById(String id) {
//        return null;
//    }
//
//    @Override
//    public List<DoctorDto> findByName(String name) {
//        return null;
//    }
//
    @Override
    public String delete(Doctor doctor) throws IOException {
        logger.info("Delete Doctor with id: "+doctor.getId());
        Optional<Doctor> doctorToDelete = doctorRepository.findById(doctor.getId());
        if(doctorToDelete.isEmpty()){
            return null;
        }
        doctorRepository.delete(doctorToDelete.get());
        return "success";
    }
//
//    @Override
//    public Doctor edit(Doctor doctor) {
//        return null;
//    }
//
    @Override
    public String save(Doctor doctor) {
        doctorRepository.save(doctor);
        return doctor.getName();
    }

    @Override
    public DoctorDto findById(Long id) {
        DoctorDto doctorDto = new DoctorDto();
        Optional<Doctor> song = doctorRepository.findById(id);
        if (song.isPresent()) {
            doctorDto.clone(song.get());
            return doctorDto;
        } else {
            return null;
        }
    }
}
