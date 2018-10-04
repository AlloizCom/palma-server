package com.alloiz.palma.server.repository.utils;

import java.sql.Timestamp;
import java.util.List;

public class ChangeRoomForSale {

    private List<RoomTypeWithNumber> rooms;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    private List<String> daysOfWeek;

    public ChangeRoomForSale() {
    }

    public List<RoomTypeWithNumber> getRooms() {
        return rooms;
    }

    public ChangeRoomForSale setRooms(List<RoomTypeWithNumber> rooms) {
        this.rooms = rooms;
        return this;
    }

    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public ChangeRoomForSale setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public ChangeRoomForSale setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public List<String> getDaysOfWeek() {
        return daysOfWeek;
    }

    public ChangeRoomForSale setDaysOfWeek(List<String> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
        return this;
    }

    @Override
    public String toString() {
        return "ChangeRoomForSale{" +
                "rooms=" + rooms +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", daysOfWeek=" + daysOfWeek +
                '}';
    }
}
