package com.ftn.eventsorganization.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.eventsorganization.enumeration.EventType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    private EventType eventType;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "event")
    private List<EventSector> eventSector;

    private boolean deleted;

    public Event(String name, Date startDate, Date endDate, EventType eventType, Location location) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventType = eventType;
        this.location = location;
        this.deleted = false;
    }

    public Event() {
        this.deleted = false;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<EventSector> getEventSector() {
        return eventSector;
    }

    public void setEventSector(List<EventSector> eventSector) {
        this.eventSector = eventSector;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
