package com.ftn.eventsorganization.DTO;

public class HallDTO {

    private String name;

    private Long locationId;

    public HallDTO(String name, Long locationId) {
        this.name = name;
        this.locationId = locationId;
    }

    public HallDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
}
