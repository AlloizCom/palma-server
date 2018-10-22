package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.enums.TariffType;
import com.alloiz.palma.server.model.utils.DateDeserializer;
import com.alloiz.palma.server.model.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Tariff extends BaseEntity<Tariff> {

    private Integer price;
    private Timestamp dateFrom;
    private Timestamp dateTo;

    @ManyToOne(cascade = CascadeType.DETACH)
    private RoomForSale roomForSale;

    public Tariff() {
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

    public RoomForSale getRoomForSale() {
        return roomForSale;
    }

    public Tariff setRoomForSale(RoomForSale roomForSale) {
        this.roomForSale = roomForSale;
        return this;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                ", price=" + price +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
