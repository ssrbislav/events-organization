package com.ftn.eventsorganization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.eventsorganization.enumeration.SectorType;

import javax.persistence.*;
import java.util.List;

@Entity
public class EventSector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "event_id")
    private Event event;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @OneToMany(mappedBy = "eventSector")
    private List<Ticket> tickets;

    double price;

    // Type of sector -> VIP, REGULAR ...
    private SectorType sectorType;

    public EventSector() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SectorType getSectorType() {
        return sectorType;
    }

    public void setSectorType(SectorType sectorType) {
        this.sectorType = sectorType;
    }
}
