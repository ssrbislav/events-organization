package com.ftn.eventsorganization.service.impl;

import com.ftn.eventsorganization.DTO.RegistrationDTO;
import com.ftn.eventsorganization.enumeration.RoleType;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.model.Role;
import com.ftn.eventsorganization.model.Visitor;
import com.ftn.eventsorganization.repository.RoleRepository;
import com.ftn.eventsorganization.repository.UserRepository;
import com.ftn.eventsorganization.repository.VisitorRepository;
import com.ftn.eventsorganization.service.IVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class VisitorService implements IVisitorService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Visitor create(RegistrationDTO dto) throws InvalidInputException {

        Boolean email = userRepository.existsByEmail(dto.getEmail());
        if (email) {
            throw new InvalidInputException("Visitor with email: " + dto.getEmail() + " already exists!");
        }

        Optional<Visitor> visitor;
        try {
            visitor = visitorRepository.findByUsername(dto.getUsername());
            if (!visitor.isPresent()) {
                visitor = Optional.of(new Visitor());
                visitor.get().setUsername(dto.getUsername());
                visitor.get().setPassword(encoder.encode(dto.getPassword()));
                visitor.get().setEmail(dto.getEmail());
                visitor.get().setFirstName(dto.getFirstName());
                visitor.get().setLastName(dto.getLastName());
                visitor.get().setDateOfBirth(dto.getDateOfBirth());
                visitor.get().setAddress(dto.getAddress());
                visitor.get().setPhoneNumber(dto.getPhoneNumber());
                visitor.get().setReservations(new ArrayList<>());

                Role role = new Role(RoleType.ROLE_VISITOR);
                Set<Role> roles = new HashSet<>();
                roles.add(role);
                visitor.get().setRoles(roles);
                visitor.get().setActive(false);
                visitor.get().setBlocked(false);

                return visitorRepository.save(visitor.get());
            } else {
                throw new InvalidInputException("Passenger with username: " + dto.getUsername() + " already exists!");
            }
        } catch (InvalidInputException ex) {
            ex.printStackTrace();
            throw new InvalidInputException("Passenger with username: " + dto.getUsername() + " already exists!", ex);
        }
    }
}
