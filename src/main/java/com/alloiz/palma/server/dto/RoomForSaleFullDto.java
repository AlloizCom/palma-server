package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.Amenity;
import com.alloiz.palma.server.model.RoomForSaleDescription;

import java.util.List;

@Dto
public class RoomForSaleFullDto extends RoomForSaleShortDto<RoomForSaleFullDto> {

    private List<Amenity> amenities;
    private List<Amenity> additionalAmenities;
    private List<RoomForSaleDescription> descriptions;

    public RoomForSaleFullDto() {
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public RoomForSaleFullDto setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
        return this;
    }

    public List<Amenity> getAdditionalAmenities() {
        return additionalAmenities;
    }

    public RoomForSaleFullDto setAdditionalAmenities(List<Amenity> additionalAmenities) {
        this.additionalAmenities = additionalAmenities;
        return this;
    }

    public List<RoomForSaleDescription> getDescriptions() {
        return descriptions;
    }

    public RoomForSaleFullDto setDescriptions(List<RoomForSaleDescription> descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    @Override
    public String toString() {
        return "RoomForSaleFullDto{" +
                "amenities=" + amenities +
                ", additionalAmenities=" + additionalAmenities +
                ", descriptions=" + descriptions +
                ", id=" + id +
                ", available=" + available +
                ", places=" + places +
                ", square=" + square +
                ", picturePath='" + picturePath + '\'' +
                ", number=" + number +
                '}';
    }
}
