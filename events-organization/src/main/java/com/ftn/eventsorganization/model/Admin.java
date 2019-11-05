package com.ftn.eventsorganization.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class Admin extends User {

    @OneToMany(mappedBy = "admin")
    private List<Event> events;

    public Admin() {
    }

    public Admin(String username, String password, @Email String email, String firstName, String lastName, String address, String phoneNumber, Set<Role> roles) {
        super(username, password, email, firstName, lastName, address, phoneNumber, roles);
    }

}
