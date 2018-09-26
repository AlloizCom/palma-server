package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.model.Amenity;
import com.alloiz.palma.server.model.RoomDescription;

import java.util.List;

public class RoomWithOnlyOneImage extends RoomShortDto<RoomWithOnlyOneImage>{

    protected List<RoomDescription> descriptions;
    protected List<Amenity> amenities;
    private String oneImagePath;

    public RoomWithOnlyOneImage() {
    }

    public List<RoomDescription> getDescriptions() {
        return descriptions;
    }

    public RoomWithOnlyOneImage setDescriptions(List<RoomDescription> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public RoomWithOnlyOneImage setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
        return this;
    }

    public String getOneImagePath() {
        return oneImagePath;
    }

    public RoomWithOnlyOneImage setOneImagePath(String oneImagePath) {
        this.oneImagePath = oneImagePath;
        return this;
    }

    @Override
    public String toString() {
        return "RoomWithOnlyOneImage{" +
                "descriptions=" + descriptions +
                ", amenities=" + amenities +
                ", oneImagePath='" + oneImagePath + '\'' +
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
