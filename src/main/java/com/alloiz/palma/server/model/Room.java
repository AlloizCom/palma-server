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
    private List<String> imagiesPath;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomDescription> descriptions;

    @OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Option> options;

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

    public List<String> getImagiesPath() {
        return imagiesPath;
    }

    public Room setImagiesPath(List<String> imagiesPath) {
        this.imagiesPath = imagiesPath;
        return this;
    }

    public List<RoomDescription> getDescriptions() {
        return descriptions;
    }

    public Room setDescriptions(List<RoomDescription> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    public List<Option> getOptions() {
        return options;
    }

    public Room setOptions(List<Option> options) {
        this.options = options;
        return this;
    }

    @Override
    public String toString() {
        return "Room{" +
                "type=" + type +
                ", adultPlaces=" + adultPlaces +
                ", kidsPlaces=" + kidsPlaces +
                ", square=" + square +
                ", imagiesPath=" + imagiesPath +
                ", descriptions=" + (descriptions == null ? "null" : descriptions) +
                ", options=" + (options == null ? "null" : descriptions) +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
