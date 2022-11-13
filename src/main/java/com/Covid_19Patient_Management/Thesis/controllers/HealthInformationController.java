package com.Covid_19Patient_Management.Thesis.controllers;

import com.Covid_19Patient_Management.Thesis.dtos.HealthDeclarationDto;
//import com.Covid_19Patient_Management.Thesis.dtos.HealthInfoDtoForDoctor;
import com.Covid_19Patient_Management.Thesis.models.*;
import com.Covid_19Patient_Management.Thesis.payload.response.ResponseObject;
import com.Covid_19Patient_Management.Thesis.repository.*;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private NurseRepository nurseRepository;
    @Autowired
    private NurseServiceImplementation nurseService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientServiceImplementation patientService;
    @Autowired
    private HealthInformationRepository healthInformationRepository;
    @Autowired
    private HealthInformationServiceImplementation healthInformationService;
    @Autowired
    private TreatmentDurationRepository treatmentDurationRepository;
    @Autowired
    private TreatmentDurationServiceImplementation treatmentDurationService;
    @PostMapping(value = "/addHealthDeclaration")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<?> addHealthDeclaration(
            @RequestParam Long id,
            @RequestParam int blood_pressure,
            @RequestParam int oxygen_level,
            @RequestParam String other_diagnose,
            @RequestParam String fever,
            @RequestParam String headache,
            @RequestParam String muscleache
    ) throws MessagingException, UnsupportedEncodingException {
        Date date = new Date();
        healthInformationRepository.healthDeclaration(id, blood_pressure, oxygen_level, fever, headache, muscleache, other_diagnose, "Patient" ,date);
        Optional<Patient> patient = patientRepository.findById(id);
        Optional<Doctor> doctor = doctorRepository.findById(patient.get().getDoctor().getId());
        Optional<User> user = userRepository.findById(doctor.get().getUser().getId());
        //String email = user.get().getEmail();
        String email = "nguyenhlong0910@gmail.com";
        String fromAddress = "nguyenhlong0910@gmail.com";
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

    @PostMapping(value = "/nurseAddHealthDeclaration")
    @PreAuthorize("hasRole('NURSE')")
    ResponseEntity<?> nurseAddHealthDeclaration(
            @RequestParam Long nurse_id,
            @RequestParam Long id,
            @RequestParam int blood_pressure,
            @RequestParam int oxygen_level,
            @RequestParam String other_diagnose,
            @RequestParam String fever,
            @RequestParam String headache,
            @RequestParam String muscleache,
            @RequestParam String comment_from_nurse
    ) throws MessagingException, UnsupportedEncodingException {
        Date date = new Date();
        Optional<Nurse> nurse = nurseRepository.findById(nurse_id);
        healthInformationRepository.healthDeclarationByNurse(id, blood_pressure, oxygen_level, fever,
                headache, muscleache, other_diagnose, "Nurse:" + nurse.get().getName(), comment_from_nurse, date);
        Optional<Patient> patient = patientRepository.findById(id);
        Optional<Doctor> doctor = doctorRepository.findById(patient.get().getDoctor().getId());
        Optional<User> user = userRepository.findById(doctor.get().getUser().getId());
        String email = user.get().getEmail();
//        String email = "nguyenhlong0910@gmail.com";
//        String fromAddress = "nguyenhlong0910@gmail.com";
//        String senderName = "Patient_Management_Admin";
//        String subject = "Notification from Patient";
//        String content = "Dear [[name]], username [[username]]<br>"
//                + "Your Patient - [[PatientName]] has add a health declaration<br>"
//                + "Please check it out.<br>"
//                + "Thank you,<br>";
//
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setFrom(fromAddress, senderName);
//        helper.setTo(email);
//        helper.setSubject(subject);
//
//        content = content.replace("[[name]]", doctor.get().getName());
//        content = content.replace("[[username]]", user.get().getUsername());
//        content = content.replace("[[PatientName]]", patient.get().getName());
//        helper.setText(content, true);
//        mailSender.send(message);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", email)
        );
    }

    @GetMapping(value = "/viewAllDeclaration")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<?> viewHealthDeclarations(
            @RequestParam Long id
    ){
        List<HealthInformation> list = healthInformationRepository.viewAllHealthDeclarationOfPatientId(id, PageRequest.of(0, 1000, Sort.Direction.DESC, "last_update"));
        List<HealthDeclarationDto> Dtolist = healthInformationService.listAllHealthInformationDtoOfPatient(id);
        //       List<HealthDeclarationDto> lists = healthInformationService.patientFindAllDeclaration();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", list)
        );
    }

    @GetMapping(value = "/viewPatientAllDeclaration")
    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    ResponseEntity<?> viewPatientHealthDeclarations(
            @RequestParam Long id
    ){
//        List<Object> list = healthInformationRepository.viewPatientDeclarations(doctor_id, id);
//        ArrayList<HealthInfoDtoForDoctor> list = healthInformationRepository.viewPatientDeclarations(id);
        List<HealthDeclarationDto> Dtolist = healthInformationService.listAllHealthInformationDtoOfPatient(id);
        //       List<HealthDeclarationDto> lists = healthInformationService.patientFindAllDeclaration();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", Dtolist)
        );
    }

    @GetMapping(value = "/viewPatientAllDeclarationsOnDuration")
    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    ResponseEntity<?> viewPatientAllDeclarationsOnDuration(
            @RequestParam Long id
    ){
        Optional<TreatmentDuration> treatmentDuration = treatmentDurationRepository.findById(id);
        List<HealthDeclarationDto> Dtolist = healthInformationService.listAllHealthInformationDtoOfPatient(treatmentDuration.get().getPatient().getId());
        List<HealthDeclarationDto> healthDeclarationDtos = new ArrayList<HealthDeclarationDto>();
        if(treatmentDuration.get().getEnd_date() == null){
            for(HealthDeclarationDto h : Dtolist){
                if(h.getLast_update().compareTo(treatmentDuration.get().getStart_date()) >= 0){
                    healthDeclarationDtos.add(h);
                }
            }
        } else if(treatmentDuration.get().getEnd_date() != null) {
            for(HealthDeclarationDto h : Dtolist){
                if(h.getLast_update().compareTo(treatmentDuration.get().getStart_date()) >= 0
                    && h.getLast_update().compareTo(treatmentDuration.get().getEnd_date()) <= 0){
                    healthDeclarationDtos.add(h);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", healthDeclarationDtos)
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
