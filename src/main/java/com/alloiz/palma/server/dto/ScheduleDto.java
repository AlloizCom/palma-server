package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.RoomForSale;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;

/**
 * Dto for Schedule Entity
 */
@Dto
public class ScheduleDto {

    protected Long id;
    protected Boolean available;
    private Timestamp today;
    private Integer price;
    private Boolean isFree;
    private RoomForSale room;

    public ScheduleDto() {
    }

    public Long getId() {
        return id;
    }

    public ScheduleDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public ScheduleDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getToday() {
        return today;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public ScheduleDto setToday(Timestamp today) {
        this.today = today;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ScheduleDto setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Boolean getFree() {
        return isFree;
    }

    public ScheduleDto setFree(Boolean free) {
        isFree = free;
        return this;
    }

    public RoomForSale getRoom() {
        return room;
    }

    public ScheduleDto setRoom(RoomForSale room) {
        this.room = room;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleDto{" +
                "id=" + id +
                ", available=" + available +
                ", today=" + today +
                ", price=" + price +
                ", isFree=" + isFree +
                ", room=" + room +
                '}';
    }
}
