package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.RoomType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/*
Model for room which we can sell
 */
@Entity
public class RoomOne extends BaseEntity<RoomOne> {

    private Integer places;
    private String name;
    private Integer price;
    private Double square;
    private String imagePath;
    private RoomType roomType;



    @OneToMany(cascade = CascadeType.ALL)
    private List<RoomDescription> descriptions;

    public RoomOne() {
    }

    public Integer getPlaces() {
        return places;
    }

    public RoomOne setPlaces(Integer places) {
        this.places = places;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoomOne setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public RoomOne setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Double getSquare() {
        return square;
    }

    public RoomOne setSquare(Double square) {
        this.square = square;
        return this;
    }

//    public Room getRoom() {
//        return room;
//    }
//
//    public RoomOne setRoom(Room room) {
//        this.room = room;
//        return this;
//    }

    public String getImagePath() {
        return imagePath;
    }

    public RoomOne setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public List<RoomDescription> getDescriptions() {
        return descriptions;
    }

    public RoomOne setDescriptions(List<RoomDescription> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    @Override
    public String toString() {
        return "RoomOne{" +
                "places=" + places +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", square=" + square +
                ", imagePath='" + imagePath + '\'' +
                ", descriptions=" + descriptions +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
