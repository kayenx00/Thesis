package com.Covid_19Patient_Management.Thesis.controllers;
import com.Covid_19Patient_Management.Thesis.dtos.AppointmentDto;
import com.Covid_19Patient_Management.Thesis.models.Appointment;
import com.Covid_19Patient_Management.Thesis.models.Patient;
import com.Covid_19Patient_Management.Thesis.payload.response.ResponseObject;
import com.Covid_19Patient_Management.Thesis.repository.*;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.AppointmentServiceImplementation;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.DoctorServiceImplementation;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.HealthInformationServiceImplementation;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.PatientServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RestController
public class AppointmentController {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);
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
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmentServiceImplementation appointmentService;
    @Autowired
    private HealthInformationServiceImplementation healthInformationService;
    @PostMapping("/requestAppointment")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> requestAppointment(
            @RequestParam Long patient_id,
            @RequestParam Long doctor_id,
            @RequestParam String date,
            @RequestParam String start_time,
            @RequestParam int duration) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateToAdd = sdf.parse(date);
        appointmentRepository.requestAnAppointment(patient_id, doctor_id, dateToAdd, start_time, duration, "Request", false);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", "Request successfully")
        );
    }
    @PostMapping("/initiateAppointment")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> initiateAppointment(
            @RequestParam Long doctor_id,
            @RequestParam String date,
            @RequestParam String start_time,
            @RequestParam int duration) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateToAdd = sdf.parse(date);
        appointmentRepository.initiateAnAppointment(doctor_id, dateToAdd, start_time, duration, "Initiate", false);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", "Request successfully")
        );
    }
    @PutMapping("/confirmAppointment")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> confirmAppointment(
            @RequestParam Long doctor_id,
            @RequestParam(name = "id") List<Long> list) throws ParseException, MessagingException, UnsupportedEncodingException {
        for(Long l : list){
            Optional<Appointment> appointment = appointmentRepository.findById(l);
            appointmentRepository.confirmAnAppointment(true, doctor_id, l);
            //String email = appointment.get().getPatient().getUser().getEmail();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String date = dateFormat.format(appointment.get().getDate());
            String email = "nguyenhlong0910@gmail.com";
            String fromAddress = "nguyenhlong0910@gmail.com";
            String senderName = "Patient_Management_Admin";
            String subject = "Notification from Doctor";
            String content = "Dear [[name]], username [[username]]<br>"
                    + "Your Doctor - [[DoctorName]] has confirmed your appointment request on [[date]] at [[time]].<br>"
                    + "Please check it out and be sure to attend on time.<br>"
                    + "Thank you,<br>";
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(fromAddress, senderName);
            helper.setTo(email);
            helper.setSubject(subject);

            content = content.replace("[[name]]", appointment.get().getPatient().getName());
            content = content.replace("[[username]]", appointment.get().getPatient().getUser().getUsername());
            content = content.replace("[[DoctorName]]", appointment.get().getDoctor().getName());
            content = content.replace("[[date]]", date);
            content = content.replace("[[time]]", appointment.get().getStart_time());

            helper.setText(content, true);
            mailSender.send(message);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", "Request successfully")
        );
    }
    @PutMapping("/bookAppointment")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> bookAppointment(
            @RequestParam Long patient_id,
            @RequestParam(name = "id") List<Long> list) throws ParseException, MessagingException, UnsupportedEncodingException {
        for(Long l : list){
            appointmentRepository.bookAnAppointment(true, patient_id, l);
        }
        Optional<Patient> patient = patientRepository.findById(patient_id);
        //String email = patient.get().getUser().getEmail();
        String email = "nguyenhlong0910@gmail.com";
        String fromAddress = "nguyenhlong0910@gmail.com";
        String senderName = "Patient_Management_Admin";
        String subject = "Notification from Patient";
        String content = "Dear [[name]], username [[username]]<br>"
                + "Your Patient - [[PatientName]] has booked your appointment(s).<br>"
                + "Please check it out and be sure to attend on time.<br>"
                + "Thank you,<br>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(email);
        helper.setSubject(subject);

        content = content.replace("[[name]]", patient.get().getDoctor().getName());
        content = content.replace("[[username]]", patient.get().getDoctor().getUser().getUsername());
        content = content.replace("[[PatientName]]", patient.get().getName());
        helper.setText(content, true);
        mailSender.send(message);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", "Request successfully")
        );
    }
    @GetMapping(value = "/getInitiateAppointment")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<?> getInitiateAppointment(
            @RequestParam Long doctor_id){
        List<AppointmentDto> appointments = appointmentService.findAllInitiateAppointment("Initiate", false, doctor_id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", appointments)
        );
    }
    @GetMapping(value = "/getRequestAppointment")
    @PreAuthorize("hasRole('DOCTOR')")
    ResponseEntity<?> getRequestAppointment(
            @RequestParam Long doctor_id){
        List<AppointmentDto> appointments = appointmentService.findAllRequestAppointment("Request", false, doctor_id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", appointments)
        );
    }
    @GetMapping(value = "/patientGetUpcomingAppointment")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<?> patientGetUpcomingAppointment(
            @RequestParam Long patient_id){
        List<AppointmentDto> appointments = appointmentService.patientViewUpcomingAppointment(patient_id, true);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", appointments)
        );
    }
    @GetMapping(value = "/doctorGetUpcomingAppointment")
    @PreAuthorize("hasRole('DOCTOR')")
    ResponseEntity<?> doctorGetUpcomingAppointment(
            @RequestParam Long doctor_id){
        List<AppointmentDto> appointments = appointmentService.doctorViewUpcomingAppointment(doctor_id, true);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", appointments)
        );
    }
    @DeleteMapping(value = "/rejectRequestAppointment")
    @PreAuthorize("hasRole('DOCTOR')")
    ResponseEntity<?> rejectRequestAppointment(@RequestParam(name = "id") List<Long> list){
        for(Long l : list){
            appointmentService.rejectAnAppointment(l);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", "Delete successfully")
        );
    }
    @PutMapping(value = "/patientCancelAppointment")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<?> patientCancelAppointment(@RequestParam Long id) throws MessagingException, UnsupportedEncodingException {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        appointmentRepository.patientCancelAnAppointment(false, null, id);
        //String email = appointment.get().getDoctor().getUser().getEmail();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date = dateFormat.format(appointment.get().getDate());
        String email = "nguyenhlong0910@gmail.com";
        String fromAddress = "nguyenhlong0910@gmail.com";
        String senderName = "Patient_Management_Admin";
        String subject = "Notification from Patient";
        String content = "Dear [[name]], username [[username]]<br>"
                + "Your Patient - [[PatientName]] has cancel your appointment on [[date]] at [[time]].<br>"
                + "Please check it out.<br>"
                + "Thank you,<br>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(email);
        helper.setSubject(subject);
        content = content.replace("[[name]]", appointment.get().getDoctor().getName());
        content = content.replace("[[username]]", appointment.get().getDoctor().getUser().getUsername());
        content = content.replace("[[PatientName]]", appointment.get().getPatient().getName());
        content = content.replace("[[date]]", date);
        content = content.replace("[[time]]", appointment.get().getStart_time());
        helper.setText(content, true);
        mailSender.send(message);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", "Cancel successfully")
        );
    }
    @PutMapping(value = "/doctorCancelAppointment")
    @PreAuthorize("hasRole('DOCTOR')")
    ResponseEntity<?> doctorCancelAppointment(@RequestParam Long id) throws MessagingException, UnsupportedEncodingException {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        appointmentRepository.doctorCancelAnAppointment(false, id);
        //String email = appointment.get().getPatient().getUser().getEmail();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date = dateFormat.format(appointment.get().getDate());
        String email = "nguyenhlong0910@gmail.com";
        String fromAddress = "nguyenhlong0910@gmail.com";
        String senderName = "Patient_Management_Admin";
        String subject = "Notification from Doctor";
        String content = "Dear [[name]], username [[username]]<br>"
                + "Your Doctor - [[DoctorName]] has cancel your appointment on [[date]] at [[time]].<br>"
                + "Please check it out.<br>"
                + "Thank you,<br>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(email);
        helper.setSubject(subject);
        content = content.replace("[[name]]", appointment.get().getPatient().getName());
        content = content.replace("[[username]]", appointment.get().getPatient().getUser().getUsername());
        content = content.replace("[[DoctorName]]", appointment.get().getDoctor().getName());
        content = content.replace("[[date]]", date);
        content = content.replace("[[time]]", appointment.get().getStart_time());
        helper.setText(content, true);
        mailSender.send(message);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", "Cancel successfully")
        );
    }
}
