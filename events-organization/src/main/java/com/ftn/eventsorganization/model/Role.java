package com.ftn.eventsorganization.model;

import com.ftn.eventsorganization.enumeration.RoleType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType type;

    public Role() {}

    public Role(RoleType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }
}
