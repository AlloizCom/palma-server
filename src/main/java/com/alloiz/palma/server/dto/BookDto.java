package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.enums.OrderStatus;

import java.sql.Timestamp;

@Dto
public class BookDto {
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
    protected OrderStatus orderStatus;

    public BookDto() {
    }

    public Long getId() {
        return id;
    }

    public BookDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public BookDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public BookDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BookDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BookDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BookDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Timestamp getDateIn() {
        return dateIn;
    }

    public BookDto setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
        return this;
    }

    public Timestamp getDateOut() {
        return dateOut;
    }

    public BookDto setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BookDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getAdults() {
        return adults;
    }

    public BookDto setAdults(Integer adults) {
        this.adults = adults;
        return this;
    }

    public Integer getKids() {
        return kids;
    }

    public BookDto setKids(Integer kids) {
        this.kids = kids;
        return this;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public BookDto setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    @Override
    public String toString() {
        return "BookDto{" +
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
                ", orderStatus=" + orderStatus +
                '}';
    }
}
