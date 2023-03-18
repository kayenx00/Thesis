package com.Covid_19Patient_Management.Thesis.services.serviceImp;

import com.Covid_19Patient_Management.Thesis.dtos.AppointmentDto;
import com.Covid_19Patient_Management.Thesis.dtos.TreatmentDurationDto;
import com.Covid_19Patient_Management.Thesis.models.Appointment;
import com.Covid_19Patient_Management.Thesis.models.TreatmentDuration;
import com.Covid_19Patient_Management.Thesis.repository.TreatmentDurationRepository;
import com.Covid_19Patient_Management.Thesis.services.TreatmentDurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class TreatmentDurationServiceImplementation implements TreatmentDurationService {
    @Autowired
    private TreatmentDurationRepository treatmentDurationRepository;
    @Override
    public List<TreatmentDurationDto> findAllPatientTreatmentDuration(Long patient_id) {
        List<TreatmentDuration> treatmentDurations = treatmentDurationRepository.findByPatientID(patient_id, PageRequest.of(0, 1000, Sort.Direction.DESC, "start_date"));
        List<TreatmentDurationDto> treatmentDurationDtos = new ArrayList<TreatmentDurationDto>();
        for(TreatmentDuration t : treatmentDurations){
            TreatmentDurationDto treatmentDurationDto = new TreatmentDurationDto();
            treatmentDurationDto.clone(t);
            treatmentDurationDtos.add(treatmentDurationDto);
        }
        return treatmentDurationDtos;    }
}
