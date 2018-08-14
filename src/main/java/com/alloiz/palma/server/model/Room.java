package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.RoomDescription;
import com.alloiz.palma.server.model.enums.RoomType;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room extends BaseEntity<Room> {

    @Enumerated(EnumType.STRING)
    private RoomType type;

    private Integer adultPlaces;
    private Integer kidsPlaces;
    private Double square;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomDescription> descriptions;

    private List<Option> options;

}
