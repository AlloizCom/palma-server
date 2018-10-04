package com.alloiz.palma.server.repository.utils;

import com.alloiz.palma.server.model.enums.RoomType;

public class RoomTypeWithNumber {

    private RoomType roomType;
    private Integer numberOfRoom;

    public RoomTypeWithNumber() {
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public RoomTypeWithNumber setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    public Integer getNumberOfRoom() {
        return numberOfRoom;
    }

    public RoomTypeWithNumber setNumberOfRoom(Integer numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
        return this;
    }

    @Override
    public String toString() {
        return "RoomTypeWithNumber{" +
                "roomType=" + roomType +
                ", numberOfRoom=" + numberOfRoom +
                '}';
    }
}
