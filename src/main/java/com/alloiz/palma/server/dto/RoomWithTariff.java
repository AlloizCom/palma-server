package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.model.Amenity;
import com.alloiz.palma.server.model.Image;
import com.alloiz.palma.server.model.RoomDescription;

import java.util.List;

public class RoomWithTariff extends RoomShortDto<RoomWithTariff>{

    protected List<RoomDescription> descriptions;
    protected List<Amenity> amenities;
    protected List<Image> images;

    protected Integer price;

    public RoomWithTariff() {
    }

    public List<RoomDescription> getDescriptions() {
        return descriptions;
    }

    public RoomWithTariff setDescriptions(List<RoomDescription> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public RoomWithTariff setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

    public RoomWithTariff setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public RoomWithTariff setPrice(Integer price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "RoomWithTariff{" +
                "descriptions=" + descriptions +
                ", amenities=" + amenities +
                ", images=" + images +
                ", price=" + price +
                ", id=" + id +
                ", available=" + available +
                ", type=" + type +
                ", adultPlaces=" + adultPlaces +
                ", kidsPlaces=" + kidsPlaces +
                ", square=" + square +
                ", amount=" + amount +
                '}';
    }
}
