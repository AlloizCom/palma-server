package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;

@Entity
public class Schedule extends BaseEntity<Schedule> {

    private Timestamp today;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private Integer forSale;
    private Integer active;
    private Integer free;

    public Schedule() {
        forSale = 6;
        forSale = 0;
        free = forSale - active;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getToday() {
        return today;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public Schedule setToday(Timestamp today) {
        this.today = today;
        return this;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Schedule setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    public Integer getForSale() {
        return forSale;
    }

    public Schedule setForSale(Integer forSale) {
        this.forSale = forSale;
        return this;
    }

    public Integer getActive() {
        return active;
    }

    public Schedule setActive(Integer active) {
        this.active = active;
        return this;
    }

    public Integer getFree() {
        return free;
    }

    public Schedule setFree(Integer free) {
        this.free = free;
        return this;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "today=" + today +
                ", roomType=" + roomType +
                ", forSale=" + forSale +
                ", active=" + active +
                ", free=" + free +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
