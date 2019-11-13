package com.ftn.eventsorganization.model;

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
//
//    // Number of columns in a sector
    private Long numOfColumns;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @OneToMany(mappedBy = "sector")
    private List<EventSector> eventSectors;

    public Sector() {
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

    public Long getRows() {
        return numOfRows;
    }

    public void setRows(Long numOfRows) {
        this.numOfRows = numOfRows;
    }

    public Long getColumns() {
        return numOfColumns;
    }

    public void setColumns(Long numOfColumns) {
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

    public void setEventSectors(List<EventSector> eventSector) {
        this.eventSectors = eventSectors;
    }

}
