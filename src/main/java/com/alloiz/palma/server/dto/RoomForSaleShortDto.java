package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

@Dto
public class RoomForSaleShortDto <T extends RoomForSaleShortDto>{

    protected Long id;
    protected Boolean available;
    protected Integer places;
    protected Integer square;
    protected String picturePath;
    protected Integer number;

    public RoomForSaleShortDto() {
    }

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T)this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T)this;
    }

    public Integer getPlaces() {
        return places;
    }

    public T setPlaces(Integer places) {
        this.places = places;
        return (T)this;
    }

    public Integer getSquare() {
        return square;
    }

    public T setSquare(Integer square) {
        this.square = square;
        return (T)this;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public T setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return (T) this;
    }

    public Integer getNumber() {
        return number;
    }

    public T setNumber(Integer number) {
        this.number = number;
        return (T)this;
    }

    @Override
    public String toString() {
        return "RoomForSaleShortDto{" +
                "id=" + id +
                ", available=" + available +
                ", places=" + places +
                ", square=" + square +
                ", picturePath='" + picturePath + '\'' +
                ", number=" + number +
                '}';
    }
}
