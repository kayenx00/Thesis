package com.Covid_19Patient_Management.Thesis.services;

import com.Covid_19Patient_Management.Thesis.dtos.TreatmentDurationDto;

import java.util.List;

public interface TreatmentDurationService {
    List<TreatmentDurationDto> findAllPatientTreatmentDuration(Long patient_id);
}
