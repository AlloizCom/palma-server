package com.alloiz.palma.server.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book extends BaseEntity<Book> {

    private String message;
    private Integer amountOfRooms;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private RoomForSale room;

    @ManyToMany(cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
    })
    private List<Amenity> additionalAmenities;

    public Book() {
    }

    public String getMessage() {
        return message;
    }

    public Book setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getAmountOfRooms() {
        return amountOfRooms;
    }

    public Book setAmountOfRooms(Integer amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
        return this;
    }

    public RoomForSale getRoom() {
        return room;
    }

    public Book setRoom(RoomForSale room) {
        this.room = room;
        return this;
    }

    public List<Amenity> getAdditionalAmenities() {
        return additionalAmenities;
    }

    public Book setAdditionalAmenities(List<Amenity> additionalAmenities) {
        this.additionalAmenities = additionalAmenities;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "message='" + message + '\'' +
                ", amountOfRooms=" + amountOfRooms +
                ", room=" + (room == null ? "null" : room) +
                ", additionalAmenities=" +(additionalAmenities == null ? "null" : additionalAmenities) +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
