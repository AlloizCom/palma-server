package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Amenity;
import com.alloiz.palma.server.model.AmenityName;
import com.alloiz.palma.server.repository.AmenityRepository;
import com.alloiz.palma.server.service.AmenityService;
import com.alloiz.palma.server.service.utils.FileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.alloiz.palma.server.config.mapper.JsonMapper.json;
import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class AmenityServiceImpl implements AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Override
    public Amenity findOneAvailable(Long id) {
        checkId(id);
        return amenityRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Amenity> findAllAvailable() {
        return amenityRepository.findAllByAvailable(true);
    }

    @Override
    public Amenity findOne(Long id) {
        checkId(id);
        return amenityRepository.findOne(id);
    }

    @Override
    public List<Amenity> findAll() {
        return amenityRepository.findAll();
    }

    @Override
    public Amenity save(Amenity amenity) {
        checkSave(amenity);
        amenity.getAmenityNames().stream().forEach(name -> name.setAvailable(true));
        return amenityRepository.save(amenity.setAvailable(true));
    }


    @Override
    public Amenity save(String amenityJson, MultipartFile multipartFile) {
        checkJson(amenityJson);
        Amenity amenity = json(amenityJson, Amenity.class);
        amenity.getAmenityNames().stream().forEach(name -> amenity.setAmenityNames(amenity.getAmenityNames()));
        if (multipartFile != null && !multipartFile.isEmpty())
            amenity.setImagePath(fileBuilder.saveFile(multipartFile));
        return save(amenity);
    }

    @Override
    public Amenity update(Amenity amenity) {
        checkObjectExistsById(amenity.getId(), amenityRepository);
        return amenityRepository.save(findOne(amenity.getId())
                .setAmenityNames(amenity.getAmenityNames())
                //.setRoom(amenity.getRoom())
                .setAvailable(amenity.getAvailable()));
    }

    @Override
    public Amenity update(String amenityJson, MultipartFile multipartFile) {
        checkJson(amenityJson);
        Amenity amenity = json(amenityJson, Amenity.class);
        checkObjectExistsById(amenity.getId(), amenityRepository);
        if (multipartFile != null && !multipartFile.isEmpty())
            amenity.setImagePath(fileBuilder.saveFile(multipartFile));
        return save(amenity.setAmenityNames(amenity.getAmenityNames())
                //.setRoom(amenity.getRoom())
                .setAvailable(amenity.getAvailable())
                );
    }

    @Override
    public Amenity update(String amenityJson) {
        checkJson(amenityJson);
        Amenity amenity = json(amenityJson, Amenity.class);
        checkObjectExistsById(amenity.getId(), amenityRepository);
        return save(amenity.setAmenityNames(amenity.getAmenityNames())
                //.setRoom(amenity.getRoom())
                .setAvailable(amenity.getAvailable())
        );
    }

    @Override
    public Amenity updateImage(MultipartFile multipartFile, Long id) {
        checkId(id);
        return findOne(id)
                .setImagePath(fileBuilder.saveFile(multipartFile));
    }

    @Override
    public Boolean delete(Long id) {
        try {
            amenityRepository.delete(checkObjectExistsById(id, amenityRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
