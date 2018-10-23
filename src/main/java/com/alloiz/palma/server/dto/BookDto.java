package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.Amenity;
import com.alloiz.palma.server.model.RoomForSale;
import com.alloiz.palma.server.model.enums.OrderStatus;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateDeserializerForBook;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.alloiz.palma.server.model.utils.DateSerializerForBook;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;
import java.util.List;

@Dto
public class BookDto {

    protected Long id;
    protected Boolean available;
    protected String message;
    protected Integer amountOfRooms;
    protected RoomForSale room;
    protected List<Amenity> additionalAmenities;

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

    public String getMessage() {
        return message;
    }

    public BookDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getAmountOfRooms() {
        return amountOfRooms;
    }

    public BookDto setAmountOfRooms(Integer amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
        return this;
    }

    public RoomForSale getRoom() {
        return room;
    }

    public BookDto setRoom(RoomForSale room) {
        this.room = room;
        return this;
    }

    public List<Amenity> getAdditionalAmenities() {
        return additionalAmenities;
    }

    public BookDto setAdditionalAmenities(List<Amenity> additionalAmenities) {
        this.additionalAmenities = additionalAmenities;
        return this;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", available=" + available +
                ", message='" + message + '\'' +
                ", amountOfRooms=" + amountOfRooms +
                ", room=" + room +
                ", additionalAmenities=" + additionalAmenities +
                '}';
    }
}
