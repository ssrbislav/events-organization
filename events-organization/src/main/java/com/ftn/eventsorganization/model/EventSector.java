package com.ftn.eventsorganization.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class EventSector {

//    @EmbeddedId
//    EventSectorId id;
//
//    @ManyToOne
//    @MapsId("event_id")
//    @JoinColumn(name = "event_id")
//    private Event event;
//
//    @ManyToOne
//    @MapsId("sector_id")
//    @JoinColumn(name = "sector_id")
//    private Sector sector;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @OneToMany(mappedBy = "eventSector")
    private List<Ticket> tickets;

    double price;

    public EventSector() {
    }

//    public EventSectorId getId() {
//        return id;
//    }
//
//    public void setId(EventSectorId id) {
//        this.id = id;
//    }


    public Long getId() {
        return id;
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
}
