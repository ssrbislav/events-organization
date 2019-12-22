package com.ftn.eventsorganization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sectorMark;

    // Number of rows in a sector
    private Long numOfRows;

    // Number of columns in a sector
    private Long numOfColumns;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @OneToMany(mappedBy = "sector")
    private List<EventSector> eventSectors;

    private boolean deleted;

    public Sector() {
        this.deleted = false;
    }

    public Long getId() {
        return id;
    }

    public String getSectorMark() {
        return sectorMark;
    }

    public void setSectorMark(String sectorMark) {
        this.sectorMark = sectorMark;
    }

    public Long getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(Long numOfRows) {
        this.numOfRows = numOfRows;
    }

    public Long getNumOfColumns() {
        return numOfColumns;
    }

    public void setNumOfColumns(Long numOfColumns) {
        this.numOfColumns = numOfColumns;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public List<EventSector> getEventSectors() {
        return eventSectors;
    }

    public void setEventSectors(List<EventSector> eventSectors) {
        this.eventSectors = eventSectors;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
