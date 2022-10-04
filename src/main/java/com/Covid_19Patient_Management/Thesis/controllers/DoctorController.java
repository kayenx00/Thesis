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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.DoctorServiceImplementation;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.PatientServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DoctorController {
    private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);
    @Autowired
    private DoctorServiceImplementation doctorService;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @GetMapping(value = "/getDoctorById")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> findDoctorByUserID(@RequestParam Long id){
        Optional<Doctor> doctor = doctorRepository.findDoctorByUserID(id);
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.clone(doctor.get());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", doctorDto)
        );
    }
    @GetMapping(value = "/getAllDoctors")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> findAllDoctors(){
        List<DoctorDto> doctors = doctorService.findAllDoctor();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", doctors)
        );
    }

    @GetMapping(value = "/getAllDoctorsForPatient")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<ResponseObject> findAllDoctorsForPatient(){
        List<DoctorDto> doctors = doctorService.patientFindAllDoctor();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", doctors)
        );
    }


    @PostMapping(value = "/addDoctor")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addDoctor(@Valid @RequestBody CreateDoctorRequest request){
        logger.info("Sign up with username: " + request.getUsername() + ", email: " + request.getEmail());
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        User user = new User(request.getUsername(),
                request.getEmail(),
                encoder.encode(request.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        user.setEnabled(true);
        userRepository.save(user);
        Optional<User> userToAddToDoctorTable = userRepository.findByUsername(request.getUsername());
        Long doctorUserId = userToAddToDoctorTable.get().getId();
        doctorRepository.createDoctor(request.getName(), request.getPhone(), doctorUserId);

//        }
        return ResponseEntity.ok(new MessageResponse("Doctor account created successfully!"));
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "Success",userToAddToDoctorTable.get().getId() )
//        );
    }
    @PutMapping(value = "/updateDoctor")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> updateDoctor(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String phone){
        doctorRepository.alterDoctor(name, phone, id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", "")
        );
    }

    @GetMapping(value = "/getDoctorByIdForPatient")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> patientGetDoctorInformation(@RequestParam Long id){
        Optional<Doctor> doctor = doctorRepository.findById(id);
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.clone(doctor.get());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", doctorDto)
        );
    }

    @DeleteMapping(value = "/ddeleteDoctors")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> delete(@RequestParam(name = "id") List<Long> list){
        if(list.size() >= 0){
            Doctor doctor = new Doctor();
            for(Long l : list){
                doctor.setId(l);
                String status;
                try{
                    status = doctorService.delete(doctor);

                }catch(IOException e){
                    throw new RuntimeException(e);
                }
                if (status == null) {
                    throw new DoctorNotFoundException();
                }

            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Success", null)
            );

        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("error", "Missing required ID", null)
            );
        }
    }
}
