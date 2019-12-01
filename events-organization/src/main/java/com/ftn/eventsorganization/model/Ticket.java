package com.ftn.eventsorganization.model;

import javax.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "eventSector_id")
    private EventSector eventSector;

    private boolean reserved;

    private boolean bought;

    public Ticket() {}

    public Long getId() {
        return id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public EventSector getEventSector() {
        return eventSector;
    }

    public void setEventSector(EventSector eventSector) {
        this.eventSector = eventSector;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
