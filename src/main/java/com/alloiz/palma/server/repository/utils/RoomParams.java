package com.alloiz.palma.server.repository.utils;

import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;

public class RoomParams {

    private Timestamp dateFrom;
    private Timestamp dateTo;
    private Integer numbersOfRooms;
    private Integer adults;
    private Integer childrens;
    private RoomType roomType;

    public RoomParams() {
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateFrom() {
        return dateFrom;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public RoomParams setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateTo() {
        return dateTo;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public RoomParams setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public int getNumbersOfRooms() {
        return numbersOfRooms;
    }

    public RoomParams setNumbersfRooms(int numberOfRooms) {
        this.numbersOfRooms = numberOfRooms;
        return this;
    }

    public int getAdults() {
        return adults;
    }

    public RoomParams setAdults(int adults) {
        this.adults = adults;
        return this;
    }

    public int getChildrens() {
        return childrens;
    }

    public RoomParams setChildrens(int childrens) {
        this.childrens = childrens;
        return this;
    }

    public RoomParams setNumbersOfRooms(Integer numbersOfRooms) {
        this.numbersOfRooms = numbersOfRooms;
        return this;
    }

    public RoomParams setAdults(Integer adults) {
        this.adults = adults;
        return this;
    }

    public RoomParams setChildrens(Integer childrens) {
        this.childrens = childrens;
        return this;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public RoomParams setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    @Override
    public String toString() {
        return "RoomParams{" +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", numbersOfRooms=" + numbersOfRooms +
                ", adults=" + adults +
                ", childrens=" + childrens +
                ", roomType=" + roomType +
                '}';
    }
}
