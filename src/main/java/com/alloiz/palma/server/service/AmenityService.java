package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Amenity;

import java.util.List;

public interface AmenityService {

    Amenity findOneAvailable(Long id);

    List<Amenity> findAllAvailable();

    Amenity findOne(Long id);

    List<Amenity> findAll();

    Amenity save(Amenity amenity);

    Amenity update(Amenity update);

    Boolean delete(Long id);

}
