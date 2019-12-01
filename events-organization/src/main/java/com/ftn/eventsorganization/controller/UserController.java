package com.ftn.eventsorganization.controller;

import com.ftn.eventsorganization.DTO.JwtResponse;
import com.ftn.eventsorganization.DTO.LoginDTO;
import com.ftn.eventsorganization.DTO.RegistrationDTO;
import com.ftn.eventsorganization.enumeration.RoleType;
import com.ftn.eventsorganization.model.Role;
import com.ftn.eventsorganization.model.User;
import com.ftn.eventsorganization.model.Visitor;
import com.ftn.eventsorganization.repository.RoleRepository;
import com.ftn.eventsorganization.repository.UserRepository;
import com.ftn.eventsorganization.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationDTO signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating passenger's account
        User user = new Visitor(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail(), signUpRequest.getFirstName(),
                signUpRequest.getLastName(), signUpRequest.getDateOfBirth(), signUpRequest.getAddress(),
                signUpRequest.getPhoneNumber()
        );

        Role role = new Role();
        role.setType(RoleType.VISITOR);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }
}
