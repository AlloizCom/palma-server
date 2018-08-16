package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Image;
import com.alloiz.palma.server.model.Room;
import com.alloiz.palma.server.repository.ImageRepository;
import com.alloiz.palma.server.repository.RoomRepository;
import com.alloiz.palma.server.service.RoomService;
import com.alloiz.palma.server.service.utils.FileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.alloiz.palma.server.service.utils.Validation.*;
import static com.alloiz.palma.server.config.mapper.JsonMapper.json;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Override
    public Room findOneAvailable(Long id) {
        checkId(id);
        return roomRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Room> findAllAvailable() {
        return roomRepository.findAllByAvailable(true);
    }

    @Override
    public Room findOne(Long id) {
        checkId(id);
        return roomRepository.findOne(id);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        checkSave(room);
        room.getDescriptions().stream().forEach(roomDescription -> roomDescription.setAvailable(true));
        roomRepository.save(room.setAvailable(true));
        return room;
    }

    @Override
    public Room save(String roomJson, MultipartFile[] multipartFiles) {
        checkJson(roomJson);
        Room room = json(roomJson, Room.class);
        if( multipartFiles != null && multipartFiles.length != 0){
           List<Image> images = new ArrayList<>();
            for(MultipartFile multipartFile : multipartFiles){
                Image image = new Image();
                image.setPath(fileBuilder.saveFile(multipartFile)).setAvailable(true);
                images.add(image);
            }
            room.setImages(images);
        }
        return save(room);
        }

    @Override
    public Room update(Room room) {
        checkObjectExistsById(room.getId(),roomRepository);
        return roomRepository.save(findOne(room.getId())
                .setAdultPlaces(room.getAdultPlaces())
                .setAmenities(room.getAmenities())
                .setAvailable(room.getAvailable())
                .setDescriptions(room.getDescriptions())
                .setKidsPlaces(room.getKidsPlaces())
                .setSquare(room.getSquare())
                .setType(room.getType()));
    }

    @Override
    public Room update(String roomJson) {
        checkJson(roomJson);
        Room room = json(roomJson, Room.class);
        return update(room);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            roomRepository.delete(checkObjectExistsById(id, roomRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean deleteImage(Long roomId, Long imageId) {
        checkId(imageId);
        Room room = findOne(roomId);
        List<Image> images = room.getImages();
        ListIterator<Image> listIterator = images.listIterator();
//        while (listIterator.hasNext()){
//            Image image = listIterator.next();
//            if(image.getId().equals(imageId)) {
//                listIterator.remove();
//                return false;
//            }
//        }
        for (Image image : images){
            if (image.getId().equals(imageId)){
                imageRepository.delete(imageId);
                return true;
            }
        }
        return false;
    }
}
