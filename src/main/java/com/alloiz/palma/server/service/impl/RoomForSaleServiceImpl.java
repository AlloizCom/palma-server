package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.RoomForSale;
import com.alloiz.palma.server.repository.RoomForSaleRepository;
import com.alloiz.palma.server.service.RoomForSaleService;
import com.alloiz.palma.server.service.utils.FileBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.alloiz.palma.server.config.mapper.JsonMapper.json;
import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class RoomForSaleServiceImpl implements RoomForSaleService {

    private static final Logger LOGGER = Logger.getLogger(RoomForSaleServiceImpl.class);

    @Autowired
    private RoomForSaleRepository roomForSaleRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Override
    public RoomForSale findOneAvailable(Long id) {
        checkId(id);
        return roomForSaleRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<RoomForSale> findAllAvailable() {
        return roomForSaleRepository.findAllByAvailable(true);
    }

    @Override
    public RoomForSale findOne(Long id) {
        checkId(id);
        return roomForSaleRepository.findOne(id);
    }

    @Override
    public List<RoomForSale> findAll() {
        return roomForSaleRepository.findAll();
    }

    @Override
    public RoomForSale save(RoomForSale roomForSale) {
        checkSave(roomForSale);
        roomForSale.getDescriptions()
                .stream()
                .forEach(roomDescription -> roomDescription.setAvailable(true));
        roomForSaleRepository.save(roomForSale.setAvailable(true));
        return roomForSale;
    }

    @Override
    public RoomForSale save(String roomForSaleJson, MultipartFile multipartFile) {
        checkJson(roomForSaleJson);
        RoomForSale roomForSale = json(roomForSaleJson, RoomForSale.class);
        roomForSale.getDescriptions()
                .stream()
                .forEach(roomDescription -> roomDescription.setAvailable(true));
        if (multipartFile != null && !multipartFile.isEmpty()) {
            roomForSale.setPicturePath(fileBuilder.saveFile(multipartFile));
        }
        return save(roomForSale);
    }

    @Override
    public RoomForSale update(RoomForSale roomForSale) {
        checkObjectExistsById(roomForSale.getId(), roomForSaleRepository);
        return roomForSaleRepository.save(findOne(roomForSale.getId())
                .setAvailable(roomForSale.getAvailable())
                .setDescriptions(roomForSale.getDescriptions())
                .setAmenities(roomForSale.getAmenities())
                .setAdditionalAmenities(roomForSale.getAdditionalAmenities())
                .setSquare(roomForSale.getSquare())
                .setPlaces(roomForSale.getPlaces())
        );
    }

    @Override
    public RoomForSale update(String roomForSaleJson, MultipartFile multipartFile) {
        checkJson(roomForSaleJson);
        RoomForSale roomForSale = json(roomForSaleJson, RoomForSale.class);
        if (multipartFile != null && !multipartFile.isEmpty()) {
            roomForSale.setPicturePath(fileBuilder.saveFile(multipartFile));
        }
        return update(roomForSale);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            roomForSaleRepository.delete(checkObjectExistsById(id, roomForSaleRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
