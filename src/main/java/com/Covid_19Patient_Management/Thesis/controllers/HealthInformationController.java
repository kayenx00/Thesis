package com.Covid_19Patient_Management.Thesis.controllers;

import com.Covid_19Patient_Management.Thesis.dtos.HealthDeclarationDto;
import com.Covid_19Patient_Management.Thesis.dtos.HealthInfoDtoForDoctor;
import com.Covid_19Patient_Management.Thesis.models.Doctor;
import com.Covid_19Patient_Management.Thesis.models.HealthInformation;
import com.Covid_19Patient_Management.Thesis.models.Patient;
import com.Covid_19Patient_Management.Thesis.models.User;
import com.Covid_19Patient_Management.Thesis.payload.response.ResponseObject;
import com.Covid_19Patient_Management.Thesis.repository.DoctorRepository;
import com.Covid_19Patient_Management.Thesis.repository.HealthInformationRepository;
import com.Covid_19Patient_Management.Thesis.repository.PatientRepository;
import com.Covid_19Patient_Management.Thesis.repository.UserRepository;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.DoctorServiceImplementation;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.HealthInformationServiceImplementation;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.PatientServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class HealthInformationController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository userRepository;
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
    ) throws MessagingException, UnsupportedEncodingException {
        Date date = new Date();
        healthInformationRepository.healthDeclaration(id, blood_pressure, oxygen_level, other_diagnose, date);
        Optional<Patient> patient = patientRepository.findById(id);
        Optional<Doctor> doctor = doctorRepository.findById(patient.get().getDoctor().getId());
        Optional<User> user = userRepository.findById(doctor.get().getUser().getId());
        //String email = user.get().getEmail();
        String email = "nguyenhung732k@gmail.com";
        String fromAddress = "nguyenhlong0910gmail.com";
        String senderName = "Patient_Management_Admin";
        String subject = "Notification from Patient";
        String content = "Dear [[name]], username [[username]]<br>"
                + "Your Patient - [[PatientName]] has add a health declaration<br>"
                + "Please check it out.<br>"
                + "Thank you,<br>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(email);
        helper.setSubject(subject);

        content = content.replace("[[name]]", doctor.get().getName());
        content = content.replace("[[username]]", user.get().getUsername());
        content = content.replace("[[PatientName]]", patient.get().getName());
        helper.setText(content, true);
        mailSender.send(message);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", email)
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
                new ResponseObject("ok", "success", list)
        );
    }

    @GetMapping(value = "/viewPatientAllDeclaration")
    @PreAuthorize("hasRole('DOCTOR')")
    ResponseEntity<?> viewPatientHealthDeclarations(
            @RequestParam Long id
    ){
//        List<Object> list = healthInformationRepository.viewPatientDeclarations(doctor_id, id);
        ArrayList<HealthInfoDtoForDoctor> list = healthInformationRepository.viewPatientDeclarations(id);
        List<HealthDeclarationDto> Dtolist = healthInformationService.listAllHealthinformationDto(id);
        //       List<HealthDeclarationDto> lists = healthInformationService.patientFindAllDeclaration();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", Dtolist)
        );
    }

    @PutMapping(value = "/updateHealthDeclaration")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<?> changeHealthDeclaration(
            @RequestParam int blood_pressure,
            @RequestParam int oxygen_level,
            @RequestParam String other_diagnose,
            @RequestParam Long id
    ) throws MessagingException, UnsupportedEncodingException {
        Date date = new Date();
        healthInformationRepository.updateHealthDeclaration(blood_pressure, oxygen_level, other_diagnose, date, id);
        Optional<Patient> patient = patientRepository.findById(id);
        Optional<Doctor> doctor = doctorRepository.findById(patient.get().getDoctor().getId());
        Optional<User> user = userRepository.findById(doctor.get().getUser().getId());
        String email = user.get().getEmail();
        String fromAddress = "nguyenhlong0910gmail.com";
        String senderName = "Patient_Management_Admin";
        String subject = "Notification from Patient";
        String content = "Dear [[name]], username [[username]]<br>"
                + "Your Patient - [[PatientName]] has updated their health declaration.<br>"
                + "Please check it out.<br>"
                + "Thank you,<br>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(email);
        helper.setSubject(subject);

        content = content.replace("[[name]]", doctor.get().getName());
        content = content.replace("[[username]]", user.get().getUsername());
        content = content.replace("[[PatientName]]", patient.get().getName());
        helper.setText(content, true);
        mailSender.send(message);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", email)
        );
    }

    @PutMapping(value = "/updateAdvice")
    @PreAuthorize("hasRole('DOCTOR')")
    ResponseEntity<?> makeAdvice(
            @RequestParam String advice,
            @RequestParam Long id
    ) throws MessagingException, UnsupportedEncodingException {
        healthInformationRepository.updateAdvice(advice, id);
        Optional<HealthInformation> healthInformation = healthInformationRepository.findById(id);
        Optional<Patient> patient = patientRepository.findById(healthInformation.get().getPatient().getId());
        Optional<User> user = userRepository.findById(patient.get().getUser().getId());
        String email = user.get().getEmail();
        String fromAddress = "nguyenhlong0910gmail.com";
        String senderName = "Patient_Management_Admin";
        String subject = "Notification from Doctor";
        String content = "Dear [[name]], username [[username]]<br>"
                + "Your Doctor has given an advice to your health information<br>"
                + "Please check it out.<br>"
                + "Thank you,<br>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(email);
        helper.setSubject(subject);

        content = content.replace("[[name]]", patient.get().getName());
        content = content.replace("[[username]]", user.get().getUsername());
        helper.setText(content, true);
        mailSender.send(message);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", email)
        );
    }

}
