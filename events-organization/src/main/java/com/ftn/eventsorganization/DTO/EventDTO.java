package com.ftn.eventsorganization.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.eventsorganization.enumeration.EventType;

import java.util.Date;

public class EventDTO {

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    private EventType eventType;

    private Long locationId;

    public EventDTO(String name, Date startDate, Date endDate, EventType eventType, Long locationId) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventType = eventType;
        this.locationId = locationId;
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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
}
