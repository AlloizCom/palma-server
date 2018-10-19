package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.Language;

import javax.persistence.*;

@Entity
public class RoomDescription extends BaseEntity<RoomDescription> {

    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private String roomTypeName;

    public RoomDescription() {
    }

    public Language getLanguage() {
        return language;
    }

    public RoomDescription setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RoomDescription setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public RoomDescription setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
        return this;
    }

    @Override
    public String toString() {
        return "RoomDescription{" +
                "language=" + (language == null ? "null" : language) +
                ", description='" + description + '\'' +
                ", roomTypeName='" + roomTypeName + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
