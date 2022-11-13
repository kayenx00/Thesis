package com.Covid_19Patient_Management.Thesis.controllers;

import com.Covid_19Patient_Management.Thesis.dtos.NurseDto;
import com.Covid_19Patient_Management.Thesis.models.*;
import com.Covid_19Patient_Management.Thesis.payload.request.CreateNurseRequest;
import com.Covid_19Patient_Management.Thesis.payload.response.MessageResponse;
import com.Covid_19Patient_Management.Thesis.payload.response.ResponseObject;
import com.Covid_19Patient_Management.Thesis.repository.DoctorRepository;
import com.Covid_19Patient_Management.Thesis.repository.NurseRepository;
import com.Covid_19Patient_Management.Thesis.repository.RoleRepository;
import com.Covid_19Patient_Management.Thesis.repository.UserRepository;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.DoctorServiceImplementation;
import com.Covid_19Patient_Management.Thesis.services.serviceImp.NurseServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class NurseController {
    private static final Logger logger = LoggerFactory.getLogger(NurseController.class);
    @Autowired
    private DoctorServiceImplementation doctorService;
    @Autowired
    private NurseServiceImplementation nurseService;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private NurseRepository nurseRepository;
    @GetMapping(value = "/getNurseById")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<?> findNurseByUserID(@RequestParam Long id){
        Optional<Nurse> nurse = nurseRepository.findNurseByUserID(id);
        NurseDto nurseDto = new NurseDto();
        nurseDto.clone(nurse.get());
        nurseDto.setWork_under_doctor(nurse.get().getDoctor().getName());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", nurseDto)
        );
    }
    @GetMapping(value = "/getAllNurses")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> findAllNurse(){
        List<NurseDto> nurses = nurseService.findAllNurse();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", nurses)
        );
    }
    @PostMapping(value = "/addNurse")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addNurse(@Valid @RequestBody CreateNurseRequest request){
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
        Role userRole = roleRepository.findByName(ERole.ROLE_NURSE)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        user.setEnabled(true);
        userRepository.save(user);
        Optional<User> userToAddToNurseTable = userRepository.findByUsername(request.getUsername());
        Long nurseUserId = userToAddToNurseTable.get().getId();
        nurseRepository.createNurse(request.getName(), request.getPhone(), nurseUserId);

//        }
        return ResponseEntity.ok(new MessageResponse("Doctor account created successfully!"));
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "Success",userToAddToDoctorTable.get().getId() )
//        );
    }
    @PutMapping(value = "/updateNurse")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> updateNurse(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String phone){
        nurseRepository.alterNurse(name, phone, id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", "")
        );
    }

//    @GetMapping(value = "/getDoctorByIdForPatient")
//    @PreAuthorize("hasRole('PATIENT')")
//    public ResponseEntity<?> patientGetDoctorInformation(@RequestParam Long id){
//        Optional<Doctor> doctor = doctorRepository.findById(id);
//        DoctorDto doctorDto = new DoctorDto();
//        doctorDto.clone(doctor.get());
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "success", doctorDto)
//        );
//    }
//
    @PutMapping(value = "/assignNurses")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> assign(@RequestParam(name = "id") List<Long> list, @RequestParam Long doctor_id){
        if(list.size() >=0){
            for(Long l : list){
                nurseRepository.assignNurseToDoctor(doctor_id, l);
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Success", null)
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("error", "Missing required ID", null)
            );
        }
    }

    @PutMapping(value = "/cancelAssignNurses")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> cancelAssignNurses(@RequestParam(name = "id") List<Long> list){
        if(list.size() >=0){
            for(Long l : list){
                nurseRepository.cancelAssignNurseToDoctor(null, l);
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Success", null)
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("error", "Missing required ID", null)
            );
        }
    }
    @GetMapping(value = "/getNurseOfDoctor")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<?> getNurseOfDoctor(@RequestParam Long doctor_id){
        List<NurseDto> nurses = nurseService.findNurseOfDoctor(doctor_id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", nurses)
        );
    }


    @GetMapping(value = "/getUnassignedNurse")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getUnassignedNurse(){
        List<NurseDto> nurses = nurseService.findUnassignedNurse(null);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", nurses)
        );
    }

//    @DeleteMapping(value = "/ddeleteDoctors")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<ResponseObject> delete(@RequestParam(name = "id") List<Long> list){
//        if(list.size() >= 0){
//            Doctor doctor = new Doctor();
//            for(Long l : list){
//                doctor.setId(l);
//                String status;
//                try{
//                    status = doctorService.delete(doctor);
//                }catch(IOException e){
//                    throw new RuntimeException(e);
//                }
//                if (status == null) {
//                    throw new DoctorNotFoundException();
//                }
//
//            }
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Success", null)
//            );
//
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
//                    new ResponseObject("error", "Missing required ID", null)
//            );
//        }
//    }
}
