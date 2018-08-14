package com.alloiz.palma.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Option extends BaseEntity<Option>{
    private String imagePath;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Room room;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    private List<OptionName> optionNames;

    public Option() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public Option setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public Option setRoom(Room room) {
        this.room = room;
        return this;
    }

    public List<OptionName> getOptionNames() {
        return optionNames;
    }

    public Option setOptionNames(List<OptionName> optionNames) {
        this.optionNames = optionNames;
        return this;
    }

    @Override
    public String toString() {
        return "Option{" +
                "imagePath='" + imagePath + '\'' +
                ", room=" + (room == null ? "null" : room)+
                ", optionNames=" + (optionNames == null ? "null" : optionNames)+
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
