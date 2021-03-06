package com.alloiz.palma.server.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Amenity extends BaseEntity<Amenity>{
    private String imagePath;

    private Integer price;

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//    private Room room;

    @OneToMany(cascade = {CascadeType.ALL})
//    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "amenityName_id")
    private List<AmenityName> amenityNames;

    public Amenity() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public Amenity setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

//    public Room getRoom() {
//        return room;
//    }
//
//    public Amenity setRoom(Room room) {
//        this.room = room;
//        return this;
//    }

    public List<AmenityName> getAmenityNames() {
        return amenityNames;
    }

    public Amenity setAmenityNames(List<AmenityName> amenityNames) {
        this.amenityNames = amenityNames;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Amenity setPrice(Integer price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Amenity{" +
                "imagePath='" + imagePath + '\'' +
//                ", room=" + (room == null ? "null" : room)+
                ", amanityNames=" + (amenityNames == null ? "null" : amenityNames)+
                ", price=" + price +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
