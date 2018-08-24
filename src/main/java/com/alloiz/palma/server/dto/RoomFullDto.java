package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.model.Amenity;
import com.alloiz.palma.server.model.Image;
import com.alloiz.palma.server.model.RoomDescription;

import java.util.List;

public class RoomFullDto extends RoomShortDto<RoomFullDto>{
    private List<RoomDescription> descriptions;
//    private List<Amenity> amenities;
    private List<Image> images;

    public RoomFullDto() {
    }

    public List<RoomDescription> getDescriptions() {
        return descriptions;
    }

    public RoomFullDto setDescriptions(List<RoomDescription> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

//    public List<Amenity> getAmenities() {
//        return amenities;
//    }
//
//    public RoomFullDto setAmenities(List<Amenity> amenities) {
//        this.amenities = amenities;
//        return this;
//    }

    public List<Image> getImages() {
        return images;
    }

    public RoomFullDto setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    @Override
    public String toString() {
        return "RoomFullDto{" +
                "descriptions=" + descriptions +
//                ", amenities=" + amenities +
                ", images=" + images +
                ", id=" + id +
                ", available=" + available +
                ", type=" + type +
                ", adultPlaces=" + adultPlaces +
                ", kidsPlaces=" + kidsPlaces +
                ", square=" + square +
                '}';
    }
}
