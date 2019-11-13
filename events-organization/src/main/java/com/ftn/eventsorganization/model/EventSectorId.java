package com.ftn.eventsorganization.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

//@Embeddable
public class EventSectorId implements Serializable {

    @Column(name = "sector_id")
    Long sectorId;

    @Column(name = "event_id")
    Long eventId;

}
