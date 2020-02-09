package com.ftn.eventsorganization.DTO;

import com.ftn.eventsorganization.enumeration.SectorType;

public class EventSectorDTO {

    private Long sectorId;

    private Long eventId;

    private double price;

    private SectorType sectorType;

    public EventSectorDTO() {
    }

    public EventSectorDTO(Long sectorId, Long eventId, double price, SectorType sectorType) {
        this.sectorId = sectorId;
        this.eventId = eventId;
        this.price = price;
        this.sectorType = sectorType;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
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
