package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.enums.TariffType;

import java.sql.Timestamp;

@Dto
public class TariffDto {

    protected Long id;
    protected Boolean available;
    protected TariffType tariffType;
    protected Integer price;
    protected Timestamp dateFrom;
    protected Timestamp dateTo;
    protected RoomType roomType;

    public TariffDto() {
    }

    public Long getId() {
        return id;
    }

    public TariffDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public TariffDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public TariffType getTariffType() {
        return tariffType;
    }

    public TariffDto setTariffType(TariffType tariffType) {
        this.tariffType = tariffType;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public TariffDto setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public TariffDto setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public TariffDto setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public TariffDto setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    @Override
    public String toString() {
        return "TariffDto{" +
                "id=" + id +
                ", available=" + available +
                ", tariffType=" + tariffType +
                ", price=" + price +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", roomType=" + roomType +
                '}';
    }
}
