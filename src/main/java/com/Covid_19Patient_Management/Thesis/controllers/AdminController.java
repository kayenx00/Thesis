package com.Covid_19Patient_Management.Thesis.controllers;


import com.Covid_19Patient_Management.Thesis.dtos.DoctorDto;
import com.Covid_19Patient_Management.Thesis.dtos.PatientDto;
import com.Covid_19Patient_Management.Thesis.exception.DoctorNotFoundException;
import com.Covid_19Patient_Management.Thesis.models.Doctor;
import com.Covid_19Patient_Management.Thesis.models.ERole;
import com.Covid_19Patient_Management.Thesis.models.Role;
import com.Covid_19Patient_Management.Thesis.models.User;
import com.Covid_19Patient_Management.Thesis.payload.request.CreateDoctorRequest;
import com.Covid_19Patient_Management.Thesis.payload.response.MessageResponse;
import com.Covid_19Patient_Management.Thesis.payload.response.ResponseObject;
import com.Covid_19Patient_Management.Thesis.repository.DoctorRepository;
import com.Covid_19Patient_Management.Thesis.repository.RoleRepository;
import com.Covid_19Patient_Management.Thesis.repository.UserRepository;
import com.Covid_19Patient_Management.Thesis.services.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.DoctorServiceImplementation;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.PatientServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorServiceImplementation doctorService;
    @Autowired
    private PatientServiceImplementation patientService;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

}
