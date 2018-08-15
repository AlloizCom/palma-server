package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Amenity;
import com.alloiz.palma.server.repository.AmenityRepository;
import com.alloiz.palma.server.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class AmenityServiceImpl implements AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

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
        return amenityRepository.save(amenity.setAvailable(true));
    }

    @Override
    public Amenity update(Amenity amenity) {
        checkObjectExistsById(amenity.getId(), amenityRepository);
        return amenityRepository.save(findOne(amenity.getId())
                .setAmenityNames(amenity.getAmenityNames())
                .setRoom(amenity.getRoom())
                .setAvailable(amenity.getAvailable()));
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
