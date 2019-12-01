package com.ftn.eventsorganization.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

public class RegistrationDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    private Set<String> role;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    public RegistrationDTO() {
    }

    public RegistrationDTO(@NotBlank String email, @NotBlank String username, Set<String> role, @NotBlank String password, @NotBlank String firstName,
                           @NotBlank String lastName, Date dateOfBirth, @NotBlank String address, @NotBlank String phoneNumber) {
        this.email = email;
        this.username = username;
        this.role = role;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
