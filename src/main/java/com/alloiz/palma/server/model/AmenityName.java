package com.alloiz.palma.server.model;

import com.alloiz.palma.server.model.enums.Language;

import javax.persistence.*;

@Entity
public class AmenityName extends BaseEntity<AmenityName>{
    private String name;

    @Enumerated(EnumType.STRING)

    private Language language;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private Amenity amenity;

    public AmenityName() {
    }

    public String getName() {
        return name;
    }

    public AmenityName setName(String name) {
        this.name = name;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public AmenityName setLanguage(Language language) {
        this.language = language;
        return this;
    }

//    public Amenity getAmenity() {
//        return amenity;
//    }
//
//    public AmenityName setAmenity(Amenity amenity) {
//        this.amenity = amenity;
//        return this;
//    }


    @Override
    public String toString() {
        return "AmenityName{" +
                "name='" + name + '\'' +
                ", language=" + language +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
