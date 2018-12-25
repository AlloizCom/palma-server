package com.alloiz.palma.server.dto;

import com.alloiz.palma.server.dto.utils.annotations.Dto;
import com.alloiz.palma.server.model.SEO;
import com.alloiz.palma.server.model.enums.RoomType;

import java.util.List;

@Dto
public class RoomShortDto <T extends RoomShortDto>{

    protected Long id;
    protected Boolean available;
    protected RoomType type;
    protected Integer adultPlaces;
    protected Integer kidsPlaces;
    protected Double square;
    protected Integer amount;
    protected Integer price;
    protected List<SEO> seos;
    protected Integer priceThreePlaces;
    protected Integer priceFifthPlaces;


    public RoomShortDto() {
    }

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T) this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T) this;
    }

    public RoomType getType() {
        return type;
    }

    public T setType(RoomType type) {
        this.type = type;
        return (T) this;
    }

    public Integer getAdultPlaces() {
        return adultPlaces;
    }

    public T setAdultPlaces(Integer adultPlaces) {
        this.adultPlaces = adultPlaces;
        return (T) this;
    }

    public Integer getKidsPlaces() {
        return kidsPlaces;
    }

    public T setKidsPlaces(Integer kidsPlaces) {
        this.kidsPlaces = kidsPlaces;
        return (T) this;
    }

    public Double getSquare() {
        return square;
    }

    public T setSquare(Double square) {
        this.square = square;
        return (T) this;
    }

    public Integer getAmount() {
        return amount;
    }

    public RoomShortDto<T> setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public RoomShortDto<T> setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public List<SEO> getSeos() {
        return seos;
    }

    public RoomShortDto<T> setSeos(List<SEO> seos) {
        this.seos = seos;
        return this;
    }

    public Integer getPriceThreePlaces() {
        return priceThreePlaces;
    }

    public RoomShortDto<T> setPriceThreePlaces(Integer priceThreePlaces) {
        this.priceThreePlaces = priceThreePlaces;
        return this;
    }

    public Integer getPriceFifthPlaces() {
        return priceFifthPlaces;
    }

    public RoomShortDto<T> setPriceFifthPlaces(Integer priceFifthPlaces) {
        this.priceFifthPlaces = priceFifthPlaces;
        return this;
    }

    @Override
    public String toString() {
        return "RoomShortDto{" +
                "id=" + id +
                ", available=" + available +
                ", type=" + type +
                ", adultPlaces=" + adultPlaces +
                ", kidsPlaces=" + kidsPlaces +
                ", square=" + square +
                ", amount=" + amount +
                ", price=" + price +
                ", seos=" + seos +
                ", priceThreePlaces=" + priceThreePlaces +
                ", priceFifthPlaces=" + priceFifthPlaces +
                '}';
    }
}
