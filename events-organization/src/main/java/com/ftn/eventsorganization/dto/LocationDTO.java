package com.ftn.eventsorganization.dto;

import java.io.Serializable;

public class LocationDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String name;

    private String streetName;

    private int number;

    private String city;

    private String zipCode;

    private String country;
    
    

	public LocationDTO() {
		
	}

	public LocationDTO(String name, String streetName, int number, String city, String zipCode, String country) {
		super();
		this.name = name;
		this.streetName = streetName;
		this.number = number;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
