package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;

@Dto
public class AmenityShortDto <T extends AmenityShortDto>{

    protected Long id;
    protected String imagePath;
    protected Boolean available;
    protected Integer price;

    public AmenityShortDto() {
    }

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T)this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public T setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return (T)this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T) this;
    }

    public Integer getPrice() {
        return price;
    }

    public T setPrice(Integer price) {
        this.price = price;
        return (T) this;
    }

    @Override
    public String toString() {
        return "AmenityShortDto{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", available=" + available +
                ", price=" + price +
                '}';
    }
}
