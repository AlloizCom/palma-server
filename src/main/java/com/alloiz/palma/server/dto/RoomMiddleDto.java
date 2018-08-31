package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.Image;

import java.util.List;

@Dto
public class RoomMiddleDto extends RoomShortDto<RoomFullDto>{

    private List<Image> images;

    public RoomMiddleDto() {
    }

    public List<Image> getImages() {
        return images;
    }

    public RoomMiddleDto setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    @Override
    public String toString() {
        return "RoomMiddleDto{" +
                "images=" + images +
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
