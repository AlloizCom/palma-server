package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.enums.TariffType;
import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;

@Entity
public class Tariff extends BaseEntity<Tariff> {

    @Enumerated(EnumType.STRING)
    private TariffType tariffType;
    private Integer price;
    private Timestamp dateFrom;
    private Timestamp dateTo;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    public Tariff() {
    }

    public TariffType getTariffType() {
        return tariffType;
    }

    public Tariff setTariffType(TariffType tariffType) {
        this.tariffType = tariffType;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Tariff setPrice(Integer price) {
        this.price = price;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateFrom() {
        return dateFrom;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public Tariff setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDateTo() {
        return dateTo;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public Tariff setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Tariff setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "tariffType=" + tariffType +
                ", price=" + price +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", roomType=" + roomType +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
