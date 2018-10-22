package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Entity
public class Schedule extends BaseEntity<Schedule> {

    private Timestamp today;

    private Integer price;
    private Boolean isFree;

    @OneToOne(cascade = CascadeType.ALL)
    private RoomForSale room;

    public Schedule() {
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

    public Integer getPrice() {
        return price;
    }

    public Schedule setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Boolean getFree() {
        return isFree;
    }

    public Schedule setFree(Boolean free) {
        isFree = free;
        return this;
    }

    public RoomForSale getRoom() {
        return room;
    }

    public Schedule setRoom(RoomForSale room) {
        this.room = room;
        return this;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "today=" + today +
                ", price=" + price +
                ", isFree=" + isFree +
                ", room=" + (room == null ? "null" : room) +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
