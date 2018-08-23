package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.OrderStatus;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Book extends BaseEntity<Book> {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Timestamp dateIn;
    private Timestamp dateOut;
    private String message;
    private Integer adults;
    private Integer kids;
    private Integer amountOfRooms;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
//    @OneToMany(mappedBy = "type", cascade = CascadeType.REFRESH)
//    private List<Room> rooms;
//    @OneToMany(mappedBy = "amenity", cascade = CascadeType.REFRESH)
//    private List<Amenity> amenities;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    public Book() {
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Book setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Book setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Book setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Book setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Book setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Book setEmail(String email) {
        this.email = email;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateIn() {
        return dateIn;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public Book setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateOut() {
        return dateOut;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public Book setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Book setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getAdults() {
        return adults;
    }

    public Book setAdults(Integer adults) {
        this.adults = adults;
        return this;
    }

    public Integer getKids() {
        return kids;
    }

    public Book setKids(Integer kids) {
        this.kids = kids;
        return this;
    }

    public Integer getAmountOfRooms() {
        return amountOfRooms;
    }

    public Book setAmountOfRooms(Integer amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
        return this;
    }

    //    public List<Room> getRooms() {
//        return rooms;
//    }
//
//    public Book setRooms(List<Room> rooms) {
//        this.rooms = rooms;
//        return this;
//    }

//    public List<Amenity> getAmenities() {
//        return amenities;
//    }
//
//    public Book setAmenities(List<Amenity> amenities) {
//        this.amenities = amenities;
//        return this;
//    }

    @Override
    public String toString() {
        return "Book{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", message='" + message + '\'' +
                ", adults=" + adults +
                ", kids=" + kids +
                ", orderStatus=" + orderStatus +
//                ", rooms=" + (rooms == null ? "null" : rooms) +
//                ", amenities=" + (amenities == null ? "null" : amenities) +
                ", roomType=" + roomType +
                ", amountOfRooms=" + amountOfRooms +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
