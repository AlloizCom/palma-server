package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.AmenityName;
import com.alloiz.palma.server.model.Room;

import java.util.List;

@Dto
public class AmenityFullDto extends AmenityShortDto<AmenityFullDto>{

    protected Room room;
    protected List<AmenityName> amenityNames;

    public AmenityFullDto() {
    }

    public Room getRoom() {
        return room;
    }

    public AmenityFullDto setRoom(Room room) {
        this.room = room;
        return this;
    }

    public List<AmenityName> getAmenityNames() {
        return amenityNames;
    }

    public AmenityFullDto setAmenityNames(List<AmenityName> amenityNames) {
        this.amenityNames = amenityNames;
        return this;
    }

    @Override
    public String toString() {
        return "AmenityFullDto{" +
                "room=" + room +
                ", amenityNames=" + amenityNames +
                ", id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", available=" + available +
                '}';
    }
}
