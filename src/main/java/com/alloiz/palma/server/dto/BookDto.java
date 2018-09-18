package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.enums.OrderStatus;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
    protected RoomType roomType;
    protected Integer amountOfRooms;
    protected Timestamp bookingDay;

    public BookDto() {
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public BookDto setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
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

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateIn() {
        return dateIn;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public BookDto setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateOut() {
        return dateOut;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public BookDto setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getBookingDay() {
        return bookingDay;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public BookDto setBookingDay(Timestamp bookingDay) {
        this.bookingDay = bookingDay;
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

    public Integer getAmountOfRooms() {
        return amountOfRooms;
    }

    public BookDto setAmountOfRooms(Integer amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
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
                ", bookingDay=" + bookingDay +
                ", message='" + message + '\'' +
                ", adults=" + adults +
                ", kids=" + kids +
                ", orderStatus=" + orderStatus +
                ", roomType=" + roomType +
                ", amountOfRooms=" + amountOfRooms +
                '}';
    }
}
