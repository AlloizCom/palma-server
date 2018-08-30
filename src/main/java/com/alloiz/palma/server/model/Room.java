package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.RoomType;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room extends BaseEntity<Room> {

    @Enumerated(EnumType.STRING)
    private RoomType type;

    private Integer adultPlaces;
    private Integer kidsPlaces;
    private Double square;
    private Integer amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private List<RoomDescription> descriptions;

//    @OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//    private List<Amenity> amenities;

//    @ManyToMany(cascade = {CascadeType.REFRESH,
//            CascadeType.DETACH,
//            CascadeType.MERGE,
//            CascadeType.PERSIST})
@ManyToMany(cascade = {CascadeType.REFRESH,
        CascadeType.DETACH,
        CascadeType.MERGE,
        })
    private List<Amenity> amenities;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "room_id")
    private List<Image> images;

    public Room() {
    }

    public RoomType getType() {
        return type;
    }

    public Room setType(RoomType type) {
        this.type = type;
        return this;
    }

    public Integer getAdultPlaces() {
        return adultPlaces;
    }

    public Room setAdultPlaces(Integer adultPlaces) {
        this.adultPlaces = adultPlaces;
        return this;
    }

    public Integer getKidsPlaces() {
        return kidsPlaces;
    }

    public Room setKidsPlaces(Integer kidsPlaces) {
        this.kidsPlaces = kidsPlaces;
        return this;
    }

    public Double getSquare() {
        return square;
    }

    public Room setSquare(Double square) {
        this.square = square;
        return this;
    }

    public List<RoomDescription> getDescriptions() {
        return descriptions;
    }

    public Room setDescriptions(List<RoomDescription> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public Room setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

    public Room setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public Room setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public String toString() {
        return "Room{" +
                "type=" + type +
                ", adultPlaces=" + adultPlaces +
                ", kidsPlaces=" + kidsPlaces +
                ", square=" + square +
                ", descriptions=" + (descriptions == null ? "null" : descriptions) +
                ", amenities=" + (amenities == null ? "null" : amenities) +
                ", images=" + (images == null ? "null" : images)+
                ", amount=" + amount +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
