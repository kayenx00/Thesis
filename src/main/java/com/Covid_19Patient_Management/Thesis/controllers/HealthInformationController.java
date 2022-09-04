package com.Covid_19Patient_Management.Thesis.controllers;

import com.Covid_19Patient_Management.Thesis.dtos.HealthDeclarationDto;
import com.Covid_19Patient_Management.Thesis.models.HealthInformation;
import com.Covid_19Patient_Management.Thesis.payload.response.ResponseObject;
import com.Covid_19Patient_Management.Thesis.repository.DoctorRepository;
import com.Covid_19Patient_Management.Thesis.repository.HealthInformationRepository;
import com.Covid_19Patient_Management.Thesis.repository.PatientRepository;
import com.Covid_19Patient_Management.Thesis.services.PatientService;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.DoctorServiceImplementation;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.HealthInformationServiceImplementation;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.PatientServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class HealthInformationController {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorServiceImplementation doctorService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientServiceImplementation patientService;
    @Autowired
    private HealthInformationRepository healthInformationRepository;
    @Autowired
    private HealthInformationServiceImplementation healthInformationService;
    @PostMapping(value = "/addHealthDeclaration")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<?> addHealthDeclaration(
            @RequestParam Long id,
            @RequestParam int blood_pressure,
            @RequestParam int oxygen_level,
            @RequestParam String other_diagnose
    ){
        Date date = new Date();
        healthInformationRepository.healthDeclaration(id, blood_pressure, oxygen_level, other_diagnose, date);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", "Add successfully")
        );
    }

    @GetMapping(value = "/viewAllDeclaration")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<?> viewHealthDeclarations(
            @RequestParam Long id
    ){
        List<HealthInformation> list = healthInformationRepository.viewAllHealthDeclarationOfPatientId(id);
        List<HealthDeclarationDto> Dtolist = healthInformationService.listAllHealthinformationDto(id);
        //       List<HealthDeclarationDto> lists = healthInformationService.patientFindAllDeclaration();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", Dtolist)
        );
    }

    @GetMapping(value = "/viewPatientAllDeclaration")
    @PreAuthorize("hasRole('DOCTOR')")
    ResponseEntity<?> viewPatientHealthDeclarations(
            @RequestParam Long id,
            @RequestParam Long doctor_id
    ){
        List<Object> list = healthInformationRepository.viewPatientDeclarations(doctor_id, id);
        //List<HealthDeclarationDto> Dtolist = healthInformationService.listAllHealthinformationDto(id);
        //       List<HealthDeclarationDto> lists = healthInformationService.patientFindAllDeclaration();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", list)
        );
    }

}
