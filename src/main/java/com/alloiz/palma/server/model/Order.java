package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.Status;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Order extends BaseEntity<Order> {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Timestamp dateIn;
    private Timestamp dateOut;
    private String message;
    private Integer adults;
    private Integer kids;

    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "type", cascade = CascadeType.REFRESH)
    private List<Room> rooms;
    @OneToMany(mappedBy = "amenity", cascade = CascadeType.REFRESH)
    private List<AmenityName> amenities;

    public Order() {
    }

    public Status getStatus() {
        return status;
    }

    public Order setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Order setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Order setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Order setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Order setEmail(String email) {
        this.email = email;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateIn() {
        return dateIn;
    }

    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    public Order setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateOut() {
        return dateOut;
    }

    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    public Order setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Order setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getAdults() {
        return adults;
    }

    public Order setAdults(Integer adults) {
        this.adults = adults;
        return this;
    }

    public Integer getKids() {
        return kids;
    }

    public Order setKids(Integer kids) {
        this.kids = kids;
        return this;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Order setRooms(List<Room> rooms) {
        this.rooms = rooms;
        return this;
    }

    public List<AmenityName> getAmenities() {
        return amenities;
    }

    public Order setAmenities(List<AmenityName> amenities) {
        this.amenities = amenities;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", message='" + message + '\'' +
                ", adults=" + adults +
                ", kids=" + kids +
                ", status=" + status +
                ", rooms=" + (rooms == null ? "null" : rooms) +
                ", amenities=" + (amenities == null ? "null" : amenities) +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
