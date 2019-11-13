package com.ftn.eventsorganization.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class Visitor extends User {

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean blocked;

    @OneToMany(mappedBy = "visitor")
    private List<Reservation> reservations;

    public Visitor(boolean active, boolean blocked) {
        this.active = active;
        this.blocked = blocked;
    }

    public Visitor(String username, String password, @Email String email, String firstName, String lastName, String address, String phoneNumber, Set<Role> roles, boolean active, boolean blocked) {
        super(username, password, email, firstName, lastName, address, phoneNumber, roles);
        this.active = active;
        this.blocked = blocked;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

}
