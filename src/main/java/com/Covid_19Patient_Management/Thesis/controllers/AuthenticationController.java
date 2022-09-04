package com.Covid_19Patient_Management.Thesis.controllers;


import com.Covid_19Patient_Management.Thesis.models.ERole;
import com.Covid_19Patient_Management.Thesis.models.Role;
import com.Covid_19Patient_Management.Thesis.models.User;
import com.Covid_19Patient_Management.Thesis.payload.request.LoginRequest;
import com.Covid_19Patient_Management.Thesis.payload.request.SignupRequest;
import com.Covid_19Patient_Management.Thesis.payload.response.JwtResponse;
import com.Covid_19Patient_Management.Thesis.payload.response.MessageResponse;
import com.Covid_19Patient_Management.Thesis.repository.RoleRepository;
import com.Covid_19Patient_Management.Thesis.repository.UserRepository;
import com.Covid_19Patient_Management.Thesis.security.jwt.JwtUtils;
import com.Covid_19Patient_Management.Thesis.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        logger.info("Sign up with username: " + signUpRequest.getUsername() + ", roles: " + signUpRequest.getRole());
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        String strRoles = signUpRequest.getRole();

        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_PATIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            switch(strRoles){
                case "admin" :
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).
                            orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                    roles.add(adminRole);
                    break;
                    case "doctor":
                        Role doctorRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(doctorRole);

                        break;
//                    default:
//                        Role patientRole = roleRepository.findByName(ERole.ROLE_PATIENT)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(patientRole);
            }
//            strRoles.forEach(role -> {
//                switch (roles) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "doctor":
//                        Role doctorRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(doctorRole);
//
//                        break;
//                    default:
//                        Role patientRole = roleRepository.findByName(ERole.ROLE_PATIENT)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(patientRole);
//                }
//            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
