package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Amenity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AmenityService {

    Amenity findOneAvailable(Long id);

    List<Amenity> findAllAvailable();

    Amenity findOne(Long id);

    List<Amenity> findAll();

    Amenity save(Amenity amenity);

    Amenity save(String amenityJson, MultipartFile multipartFile);

    Amenity update(Amenity amenity);

    Amenity update(String amenityJson, MultipartFile multipartFile);

    Amenity update(String amenityJson);

    Boolean delete(Long id);

}
