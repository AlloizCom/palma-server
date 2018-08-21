package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.Image;

import java.util.List;

@Dto
public class MainPageFullDto extends MainPageShortDto<MainPageFullDto> {

    protected List<Image> images;

    public MainPageFullDto() {
    }

    public List<Image> getImages() {
        return images;
    }

    public MainPageFullDto setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    @Override
    public String toString() {
        return "MainPageFullDto{" +
                "images=" + images +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
