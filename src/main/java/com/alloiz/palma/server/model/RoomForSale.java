package com.alloiz.palma.server.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class RoomForSale extends BaseEntity<RoomForSale>{

    private Integer places;
    private Integer square;
    private String picturePath;
    private Integer number;

    @ManyToMany(cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
    })
    private List<Amenity> amenities;

    @ManyToMany(cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
    })
    private List<Amenity> additionalAmenities;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private List<RoomForSaleDescription> descriptions;

    public RoomForSale() {
    }

    public Integer getPlaces() {
        return places;
    }

    public RoomForSale setPlaces(Integer places) {
        this.places = places;
        return this;
    }

    public Integer getSquare() {
        return square;
    }

    public RoomForSale setSquare(Integer square) {
        this.square = square;
        return this;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public RoomForSale setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public RoomForSale setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
        return this;
    }

    public List<Amenity> getAdditionalAmenities() {
        return additionalAmenities;
    }

    public RoomForSale setAdditionalAmenities(List<Amenity> additionalAmenities) {
        this.additionalAmenities = additionalAmenities;
        return this;
    }

    public List<RoomForSaleDescription> getDescriptions() {
        return descriptions;
    }

    public RoomForSale setDescriptions(List<RoomForSaleDescription> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public RoomForSale setNumber(Integer number) {
        this.number = number;
        return this;
    }

    @Override
    public String toString() {
        return "RoomForSale{" +
                "places=" + places +
                ", square=" + square +
                ", picturePath='" + picturePath + '\'' +
                ", amenities=" + (amenities == null ? "null" : amenities) +
                ", additionalAmenities=" + (additionalAmenities == null ? "null" : additionalAmenities) +
                ", descriptions=" + (descriptions == null ? "null" : descriptions) +
                ", number=" + number +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
