package com.ftn.eventsorganization.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "eventSector_id")
	private EventSector eventSector;

	@Column(nullable = false)
	private boolean reserved;

	@Column(nullable = false)
	private boolean bought;

	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "visitor_id", nullable = false)
	private Visitor buyer;

	private int seat_column;
	private int seat_row;

	public Ticket() {
	}

	public Ticket(Long id, Reservation reservation, EventSector eventSector, boolean reserved, boolean bought,
			Visitor buyer, int seat_column, int seat_row) {
		super();
		this.id = id;
		this.reservation = reservation;
		this.eventSector = eventSector;
		this.reserved = reserved;
		this.bought = bought;
		this.buyer = buyer;
		this.seat_column = seat_column;
		this.seat_row = seat_row;
	}

	public Ticket(Reservation reservation, EventSector eventSector, boolean reserved, boolean bought, Visitor buyer,
			int seat_column, int seat_row) {
		super();
		this.reservation = reservation;
		this.eventSector = eventSector;
		this.reserved = reserved;
		this.bought = bought;
		this.buyer = buyer;
		this.seat_column = seat_column;
		this.seat_row = seat_row;
	}

	public Visitor getBuyer() {
		return buyer;
	}

	public int getSeat_column() {
		return seat_column;
	}

	public void setSeat_column(int seat_column) {
		this.seat_column = seat_column;
	}

	public int getSeat_row() {
		return seat_row;
	}

	public void setSeat_row(int seat_row) {
		this.seat_row = seat_row;
	}

	public void setBuyer(Visitor buyer) {
		this.buyer = buyer;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
