package com.ftn.eventsorganization.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "hall")
    private List<Sector> sectors;


    public Hall() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }
}