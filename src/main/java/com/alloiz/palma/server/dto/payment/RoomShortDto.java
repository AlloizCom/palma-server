package com.alloiz.palma.server.dto.payment;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.enums.RoomType;

@Dto
public class RoomShortDto<T extends RoomShortDto> {

    private RoomType roomType;
    private String name;
    private Integer roomNumber;
    private Integer additionalPlaces;
    private Integer price;
    protected Long id;
    protected Boolean available;

    public RoomShortDto() {
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public T setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return (T)this;
    }

    public String getName() {
        return name;
    }

    public T setName(String name) {
        this.name = name;
        return (T)this;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public T setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
        return (T)this;
    }

    public Integer getAdditionalPlaces() {
        return additionalPlaces;
    }

    public T setAdditionalPlaces(Integer additionalPlaces) {
        this.additionalPlaces = additionalPlaces;
        return (T)this;
    }

    public Integer getPrice() {
        return price;
    }

    public T setPrice(Integer price) {
        this.price = price;
        return (T)this;
    }

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T)this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T)this;
    }
}
