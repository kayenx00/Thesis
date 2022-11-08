package com.Covid_19Patient_Management.Thesis.controllers;

import com.Covid_19Patient_Management.Thesis.dtos.TreatmentDurationDto;
import com.Covid_19Patient_Management.Thesis.payload.response.ResponseObject;
import com.Covid_19Patient_Management.Thesis.repository.TreatmentDurationRepository;
import com.Covid_19Patient_Management.Thesis.services.TreatmentDurationService;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.TreatmentDurationServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RestController
public class TreatmentDurationController {
    @Autowired
    private TreatmentDurationServiceImplementation treatmentDurationService;
    private TreatmentDurationRepository treatmentDurationRepository;

    @GetMapping(value = "/getTreatmentDuration")
    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    ResponseEntity<?> getTreatmentDuration(@RequestParam Long id){
        List<TreatmentDurationDto> treatmentDurationDtos = treatmentDurationService.findAllPatientTreatmentDuration(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", treatmentDurationDtos)
        );
    }
}
