package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

import java.sql.Timestamp;

@Dto
public class OrderDto {
    protected Long id;
    protected Boolean available;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String email;
    protected Timestamp dateIn;
    protected Timestamp dateOut;
    protected String message;
    protected Integer adults;
    protected Integer kids;

    public OrderDto() {
    }

    public Long getId() {
        return id;
    }

    public OrderDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public OrderDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public OrderDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public OrderDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public OrderDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public OrderDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Timestamp getDateIn() {
        return dateIn;
    }

    public OrderDto setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
        return this;
    }

    public Timestamp getDateOut() {
        return dateOut;
    }

    public OrderDto setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public OrderDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getAdults() {
        return adults;
    }

    public OrderDto setAdults(Integer adults) {
        this.adults = adults;
        return this;
    }

    public Integer getKids() {
        return kids;
    }

    public OrderDto setKids(Integer kids) {
        this.kids = kids;
        return this;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", available=" + available +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", message='" + message + '\'' +
                ", adults=" + adults +
                ", kids=" + kids +
                '}';
    }
}
