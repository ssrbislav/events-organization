package com.ftn.eventsorganization.DTO;

public class EventSectorDTO {

    private Long sectorId;

    private Long eventId;

    private double price;

    public EventSectorDTO() {
    }

    public EventSectorDTO(Long sectorId, Long eventId, double price) {
        this.sectorId = sectorId;
        this.eventId = eventId;
        this.price = price;
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
}
