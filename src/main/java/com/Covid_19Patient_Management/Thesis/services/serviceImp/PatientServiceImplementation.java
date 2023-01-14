package com.Covid_19Patient_Management.Thesis.services.serviceImp;

import com.Covid_19Patient_Management.Thesis.dtos.DoctorDto;
import com.Covid_19Patient_Management.Thesis.dtos.PatientDto;
import com.Covid_19Patient_Management.Thesis.dtos.PatientForSearchDto;
import com.Covid_19Patient_Management.Thesis.models.Doctor;
import com.Covid_19Patient_Management.Thesis.models.Patient;
import com.Covid_19Patient_Management.Thesis.models.TreatmentDuration;
import com.Covid_19Patient_Management.Thesis.repository.PatientRepository;
import com.Covid_19Patient_Management.Thesis.repository.TreatmentDurationRepository;
import com.Covid_19Patient_Management.Thesis.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PatientServiceImplementation implements PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    TreatmentDurationRepository treatmentDurationRepository;
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

    @Override
    public List<PatientForSearchDto> viewSearchedPatient(Long id, String id_num, String name) {
        List<Patient> patients = new ArrayList<>();
        if(id == null){
            patients = patientRepository.searchPatient(id_num, name);
        }
        else{
            patients = patientRepository.findByWithPatientId(id, id_num, name);
        }
        List<PatientForSearchDto> patientDtos = new ArrayList<>();
        for (Patient p : patients) {
            List<TreatmentDuration> treatmentDurations = treatmentDurationRepository.findByPatientID(p.getId(), PageRequest.of(0, 1000, Sort.Direction.DESC, "date"));
            for (TreatmentDuration t : treatmentDurations) {
                PatientForSearchDto patientForSearchDto =
                        new PatientForSearchDto(t.getId(), t.getPatient().getId(), t.getPatient().getName(), t.getPatient().getId_num(),
                                t.getDoctor().getName(), t.getStart_date(), t.getEnd_date());
                patientDtos.add(patientForSearchDto);
            }
        }
        return patientDtos;
    }
}
