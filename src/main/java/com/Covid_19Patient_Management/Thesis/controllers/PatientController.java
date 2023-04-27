package com.Covid_19Patient_Management.Thesis.controllers;

import com.Covid_19Patient_Management.Thesis.dtos.*;
import com.Covid_19Patient_Management.Thesis.models.*;
import com.Covid_19Patient_Management.Thesis.payload.request.RegisterRequest;
import com.Covid_19Patient_Management.Thesis.payload.request.SignupRequest;
import com.Covid_19Patient_Management.Thesis.payload.response.MessageResponse;
import com.Covid_19Patient_Management.Thesis.payload.response.ResponseObject;
import com.Covid_19Patient_Management.Thesis.repository.*;
import com.Covid_19Patient_Management.Thesis.services.UserService;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.NurseServiceImplementation;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.PatientServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class PatientController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientServiceImplementation patientService;
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private NurseServiceImplementation nurseService;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private TreatmentDurationRepository treatmentDurationRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    PasswordEncoder encoder;
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
    @PostMapping("/patientSignup")
    public ResponseEntity<?> registerPatient(@Valid @RequestBody RegisterRequest registerRequest) throws UnsupportedEncodingException, MessagingException {
//        logger.info("Sign up with username: " + signUpRequest.getUsername() + ", roles: " + signUpRequest.getRole());
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }
//
//        // Create new user's account
//        User user = new User(signUpRequest.getUsername(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()));
//        Set<Role> roles = new HashSet<>();
//        Role userRole = roleRepository.findByName(ERole.ROLE_PATIENT)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        roles.add(userRole);
//        user.setRoles(roles);
//        userRepository.save(user);
//        Optional<User> userToAddToPatientTable = userRepository.findByUsername(signUpRequest.getUsername());
//        patientRepository.insertIdUSerToPatient(userToAddToPatientTable.get().getId());
//        return ResponseEntity.ok(new MessageResponse("Patient registered successfully!"));
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getPassword());
        String siteURL = "http://localhost:8080/api/auth";
        userService.register(user, siteURL);
        Optional<User> userToAddToPatientTable = userRepository.findByUsername(registerRequest.getUsername());
        patientRepository.insertIdUSerToPatient(userToAddToPatientTable.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", "register successfully")
        );
    }

    @PutMapping(value = "/patientUpdate")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<?> updatePatientInformation(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String id_num,
            @RequestParam String phone,
            @RequestParam String city,
            @RequestParam String district){
        patientRepository.updatePatient(name, id_num, phone, city, district, id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", "")
        );
    }
    @GetMapping(value = "/getPatientById")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> findPatientByUserID(@RequestParam Long id){
        Optional<Patient> patient = patientRepository.findPatientByUserID(id);
        PatientDto patientDto = new PatientDto();
        patientDto.clone(patient.get());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", patientDto)
        );
    }
    @GetMapping(value = "/getAllPatients")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> findAllPatient(){
        List<PatientDto> patients = patientService.findAllPatient();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", patients)
        );
    }

    @GetMapping(value = "/getAllPatientsOfDoctor")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ResponseObject> findAllPatientOfDoctor(@RequestParam("id") Long id){
        logger.info("Get with ID: " + id);
        List<PatientDto> patients = patientService.viewPatientOfDoctor(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", patients)
        );
    }

    @GetMapping(value = "/nurseGetAllPatientsOfDoctor")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<ResponseObject> nurseGetAllPatientsOfDoctor(@RequestParam("id") Long id){
        logger.info("Get with ID: " + id);
        Optional<Nurse> nurse = nurseRepository.findById(id);
        List<PatientDto> patients = patientService.viewPatientOfDoctor(nurse.get().getDoctor().getId());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", patients)
        );
    }

    @PutMapping(value = "/registerDoctor")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> registerDoctor(@RequestParam Long chosen_doctor, @RequestParam Long id){
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isPresent()){
            if(patient.get().getDoctor() != null){
                return ResponseEntity.badRequest().body(new MessageResponse("Already register doctor"));
            }
            else{
                patientRepository.registerDoctor(id, chosen_doctor);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Success", "register successfully !")
                );
            }
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Bad Request"));
    }

    @PutMapping(value = "/newRegisterDoctor")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> newRegisterDoctor(@RequestParam Long chosen_doctor, @RequestParam Long id){
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isPresent()){
            if(patient.get().getDoctor() != null){
                return ResponseEntity.badRequest().body(new MessageResponse("Already register doctor"));
            }
            else{
                patientRepository.registerDoctor(id, chosen_doctor);
                Date date = new Date();
                treatmentDurationRepository.registerDoctor(chosen_doctor, id, date);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Success", "register successfully !")
                );
            }
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Bad Request"));
    }


    @PutMapping(value = "/endTreatmentCourse")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> endTreatmentCourse(@RequestParam Long id){
        Optional<Patient> patient = patientRepository.findById(id);
        Date date = new Date();
        if(patient.isPresent()){
            if(patient.get().getDoctor() == null){
                return ResponseEntity.badRequest().body(new MessageResponse("You did not register doctor"));
            }
            else{
                patientRepository.registerDoctor(id, null);
                treatmentDurationRepository.unRegisterDoctor(date, id, null);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Success", "Unregister successfully !")
                );
            }
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Bad Request"));
    }

    @PutMapping(value = "/endTreatmentCourseV2")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> endTreatmentCourseV2(@RequestParam Long id) throws MessagingException, UnsupportedEncodingException {
        Optional<Patient> patient = patientRepository.findById(id);
        Date date = new Date();
        String appointmentToBeCancel = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        if(patient.isPresent()){
            if(patient.get().getDoctor() == null){
                return ResponseEntity.badRequest().body(new MessageResponse("You did not register doctor"));
            }
            else{
                patientRepository.registerDoctor(id, null);
                treatmentDurationRepository.unRegisterDoctor(date, id, null);
                List<Appointment> appointments = appointmentRepository.patientUpcomingViewAppointment(id, true, PageRequest.of(0, 1000, Sort.Direction.ASC, "date"));
                List<Appointment> appoinments2 = appointmentRepository.viewRequestAppointmentForPatient("Request", patient.get().getDoctor().getId(), patient.get().getId(), false);
                for(Appointment a : appointments) {
                    if(date.compareTo(a.getDate()) <= 0){
                        appointmentRepository.patientCancelAnAppointment(false, null, a.getId());
                        appointmentToBeCancel = appointmentToBeCancel.concat("+ Date: " + dateFormat.format(a.getDate()) + ", start at " + a.getStart_time()+"<br>");
                    }
                }
                for(Appointment a : appoinments2) {
                    if(date.compareTo(a.getDate()) <= 0){
                        appointmentRepository.patientCancelAnAppointment(false, null, a.getId());
                        appointmentToBeCancel = appointmentToBeCancel.concat("+ Date: " + dateFormat.format(a.getDate()) + ", start at " + a.getStart_time()+"<br>");
                    }
                }
                String email = patient.get().getDoctor().getUser().getEmail();
//                String email = "nguyenhlong0910@gmail.com";
                String fromAddress = "nguyenhlong0910@gmail.com";
                String senderName = "Patient_Management_Admin";
                String subject = "Notification from Patient";
                String content = "Dear [[name]], username [[username]]<br>"
                        + "Your Patient - [[PatientName]] has decided to end his/her treatment with you. " +
                        "Thus, all of your upcoming appointments with them and their request appointments to you are automatically canceled. Here is the list of " +
                        "canceled appointment's date and start time. <br>"+
                        "[[appointmentToBeCancel]]"+
                        "Please check it out.<br>"+
                        "Thank you,<br>";
                logger.info(appointmentToBeCancel);
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);
                helper.setFrom(fromAddress, senderName);
                helper.setTo(email);
                helper.setSubject(subject);
                content = content.replace("[[name]]", patient.get().getDoctor().getName());
                content = content.replace("[[username]]", patient.get().getDoctor().getUser().getUsername());
                content = content.replace("[[PatientName]]", patient.get().getName());
                content = content.replace("[[appointmentToBeCancel]]", appointmentToBeCancel);
                helper.setText(content, true);
                mailSender.send(message);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Success", "Unregister successfully !")
                );
            }
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Bad Request"));
    }

    @PostMapping(value = "/searchPatient")
    public ResponseEntity<?> searchPatients(@RequestParam(required = false) Long id,
                                            @RequestParam String id_num,
                                            @RequestParam String name) {
//        if(id == null){
//            s = "";
//        } else if (id != null) {
//            s = "id = " + id;
//        }
        List<PatientForSearchDto> patientForSearchDtos = patientService.viewSearchedPatient(id, id_num, name);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", patientForSearchDtos)
        );
    }
}
