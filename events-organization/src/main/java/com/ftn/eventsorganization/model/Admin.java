package com.ftn.eventsorganization.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.Set;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class Admin extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Admin() {
    }

    public Admin(String username, String password, @Email String email, String firstName, String lastName, Date dateOfBirth, String address, String phoneNumber, Set<Role> roles) {
        super(username, password, email, firstName, lastName, dateOfBirth, address, phoneNumber, roles);
    }

    @Override
    public Long getId() {
        return id;
    }

}
