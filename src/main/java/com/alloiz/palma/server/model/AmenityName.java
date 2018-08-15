package com.alloiz.palma.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class AmenityName extends BaseEntity<AmenityName>{
    private String name;

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
         //       ", amenity=" + (amenity == null ? "null" : amenity) +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
