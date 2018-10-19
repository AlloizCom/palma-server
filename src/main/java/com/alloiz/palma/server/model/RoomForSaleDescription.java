package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.Language;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class RoomForSaleDescription extends BaseEntity<RoomForSaleDescription>{

    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private String roomTypeName;

    public RoomForSaleDescription() {
    }

    public Language getLanguage() {
        return language;
    }

    public RoomForSaleDescription setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RoomForSaleDescription setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public RoomForSaleDescription setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
        return this;
    }

    @Override
    public String toString() {
        return "RoomForSaleDescription{" +
                "language=" + language +
                ", description='" + description + '\'' +
                ", roomTypeName='" + roomTypeName + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
