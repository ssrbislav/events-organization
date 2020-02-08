package com.ftn.eventsorganization.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    @OneToMany(mappedBy = "reservation")
    private List<Ticket> tickets;

    @Column(nullable = false)
    private boolean canceled;
    
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date reservationDate;
    
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expireDate;
    
    private boolean deleted;

    public Reservation() {}
    
    

    public Reservation(Long id, Visitor visitor, List<Ticket> tickets, boolean canceled, Date reservationDate,
			Date expireDate, boolean deleted) {
		this();
		this.id = id;
		this.visitor = visitor;
		this.tickets = tickets;
		this.canceled = canceled;
		this.reservationDate = reservationDate;
		this.expireDate = expireDate;
		this.deleted = deleted;
	}



	public Long getId() {
        return id;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}


	public boolean isDeleted() {
		return deleted;
	}



	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
    
    
}
