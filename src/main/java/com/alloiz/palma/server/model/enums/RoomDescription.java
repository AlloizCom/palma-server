package com.alloiz.palma.server.model.enums;

import com.alloiz.palma.server.model.BaseEntity;
import com.alloiz.palma.server.model.Room;

import javax.persistence.*;

@Entity
public class RoomDescription extends BaseEntity<RoomDescription> {

    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Room room;

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

    public Room getRoom() {
        return room;
    }

    public RoomDescription setRoom(Room room) {
        this.room = room;
        return this;
    }

    @Override
    public String toString() {
        return "RoomDescription{" +
                "language=" + (language == null ? "null" : language) +
                ", description='" + description + '\'' +
                ", room=" + (room == null ? "null" : room) +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
