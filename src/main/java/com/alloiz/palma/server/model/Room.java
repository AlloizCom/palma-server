package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.RoomType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Entity
public class Room extends BaseEntity<Room> {

    @Enumerated(EnumType.STRING)
    private RoomType type;

    private Integer adultPlaces;
    private Integer kidsPlaces;
    private Double square;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private List<Option> options;

}
