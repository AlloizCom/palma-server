package com.alloiz.palma.server.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room extends BaseEntity<Room> {

    private Integer amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private List<RoomDescription> descriptions;

    @ManyToMany(cascade = {CascadeType.REFRESH,
        CascadeType.DETACH,
        CascadeType.MERGE,
        })
    private List<Amenity> amenities;

    @OneToMany(cascade = {CascadeType.PERSIST}, orphanRemoval=true)
    @JoinColumn(name = "room_id")
    private List<Image> images;

    public Room() {
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
                ", descriptions=" + (descriptions == null ? "null" : descriptions) +
                ", amenities=" + (amenities == null ? "null" : amenities) +
                ", images=" + (images == null ? "null" : images)+
                ", amount=" + amount +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
