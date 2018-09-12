package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
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
    protected Timestamp today;
    protected RoomType roomType;
    protected Integer forSale;
    protected Integer active;
    protected Integer free;

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

    public RoomType getRoomType() {
        return roomType;
    }

    public ScheduleDto setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    public Integer getForSale() {
        return forSale;
    }

    public ScheduleDto setForSale(Integer forSale) {
        this.forSale = forSale;
        return this;
    }

    public Integer getActive() {
        return active;
    }

    public ScheduleDto setActive(Integer active) {
        this.active = active;
        return this;
    }

    public Integer getFree() {
        return free;
    }

    public ScheduleDto setFree(Integer free) {
        this.free = free;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleDto{" +
                "id=" + id +
                ", available=" + available +
                ", today=" + today +
                ", roomType=" + roomType +
                ", forSale=" + forSale +
                ", active=" + active +
                ", free=" + free +
                '}';
    }
}
