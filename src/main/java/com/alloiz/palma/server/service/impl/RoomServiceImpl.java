package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Image;
import com.alloiz.palma.server.model.Room;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.repository.BookRepository;
import com.alloiz.palma.server.repository.ImageRepository;
import com.alloiz.palma.server.repository.RoomRepository;
import com.alloiz.palma.server.repository.utils.RoomParams;
import com.alloiz.palma.server.service.AmenityService;
import com.alloiz.palma.server.service.ImageService;
import com.alloiz.palma.server.service.RoomService;
import com.alloiz.palma.server.service.utils.FileBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.alloiz.palma.server.config.mapper.JsonMapper.json;
import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Autowired
    private ImageService imageService;

    @Autowired
    private BookRepository bookRepository;

    private static final Logger LOGGER = Logger.getLogger(RoomServiceImpl.class);


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
    public List<Room> findAllAvailableAndType(RoomType roomType) {
        return roomRepository.findAllByAvailableAndType(true, roomType);
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
        // For ManyToMany
        //room.getAmenities().stream().forEach(amenity -> amenityService.save(amenity));
        LOGGER.info("----------------");
        LOGGER.info(room.getAmenities());
        LOGGER.info("----------------");
        room.setAmenities(room.getAmenities());
        if (multipartFiles != null || multipartFiles.length != 0) {
//            List<Image> images = new ArrayList<>();
//            for (MultipartFile multipartFile : multipartFiles) {
//                Image image = new Image();
//                image.setPath(fileBuilder.saveFile(multipartFile)).setAvailable(true);
//                images.add(image);
//            }
//            room.setImages(images);
            room.setImages(imageService.saveMultiImage(multipartFiles));
        }
        return save(room);
    }

    @Override
    public Room update(Room room) {
        checkObjectExistsById(room.getId(), roomRepository);
        room.setAmenities(room.getAmenities());
        return roomRepository.save(findOne(room.getId())
                .setAdultPlaces(room.getAdultPlaces())
                .setAvailable(room.getAvailable())
                .setDescriptions(room.getDescriptions())
                .setKidsPlaces(room.getKidsPlaces())
                .setSquare(room.getSquare())
                .setAmount(room.getAmount())
                .setType(room.getType()));
    }

    @Override
    public Room update(String roomJson, MultipartFile[] multipartFiles) {
        checkJson(roomJson);
        Room room = json(roomJson, Room.class);
        checkObjectExistsById(room.getId(), roomRepository);
        if (multipartFiles != null && multipartFiles.length != 0) {
            addImages(room.getId(),multipartFiles);
        }
        room.setAmenities(room.getAmenities());
        return roomRepository.save(findOne(room.getId())
                .setAdultPlaces(room.getAdultPlaces())
                .setAvailable(room.getAvailable())
                .setDescriptions(room.getDescriptions())
                .setKidsPlaces(room.getKidsPlaces())
                .setSquare(room.getSquare())
                .setType(room.getType()))
                .setAmount(room.getAmount())
                .setImages(room.getImages())
                ;
    }

    @Override
    public Room update(String roomJson) {
        checkJson(roomJson);
        Room room = json(roomJson, Room.class);
        return update(room);
    }

    @Override
    public Room addImages(Long roomId, MultipartFile[] multipartFiles) {
        Room room = findOne(roomId);
        List<Image> imageList = room.getImages();
        for (MultipartFile file : multipartFiles) {
            Image image = new Image().setPath(fileBuilder.saveFile(file)).setAvailable(true);
            if (image.getPath().equals(""))
                continue;
            imageRepository.save(image);
            imageList.add(image);
        }
        return roomRepository.save(room);
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
    public Boolean changeAmount (RoomType roomType, Integer amount){
        List<Room> rooms = roomRepository.findAllByAvailableAndType(true, roomType);
        for (Room room : rooms){
            if (room.getType().equals(roomType)){
                room.setAmount(amount);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean addAmount(RoomType roomType, Integer amount) {
        List<Room> rooms = roomRepository.findAllByAvailableAndType(true, roomType);
        for (Room room : rooms){
            if (room.getType().equals(roomType)){
                room.setAmount(room.getAmount() + amount);
                return true;
            }
        }
        return false;

    }

    @Override
    public Boolean deleteImage(Long roomId, Long imageId) {
        LOGGER.info("----DELETE----roomID:" + roomId + "imageId:" + imageId);
        checkId(imageId);
        Room room = findOne(roomId);
        List<Image> images = room.getImages();
//        for (Image image : images){
//            if (image.getId().equals(imageId) || image.getId() == imageId){
//                System.out.println(image.getPath());
//                imageRepository.delete(imageId);
//                return true;
//            }
//        }
//        ListIterator<Image> imageListIterator = images.listIterator();
//        while (imageListIterator.hasNext()) {
//            if (imageListIterator.next().getId().equals(imageId)) {
//                imageListIterator.remove();
//                imageRepository.delete(imageId);
//                return true;
//            }
//        }
        return imageService.removeImage(images,imageId);
    }

    @Override
    public List<Room> findAllByAdultPlacesAndKidsPlacesAndAvailable(Boolean available,
                                                                    Integer kidsPlaces,
                                                                    Integer adultPlaces) {
        return roomRepository.findAllByAdultPlacesAndKidsPlacesAndAvailable(available,
                                                                            kidsPlaces,
                                                                            adultPlaces);
    }

    @Override
    public List<Room> findAllByAdultPlacesAndKidsPlacesAndAmountAndAvailable(Boolean available,
                                                                             Integer kidsPlaces,
                                                                             Integer adultPlaces,
                                                                             Integer amount) {
        return roomRepository.findAllByAdultPlacesAndKidsPlacesAndAmountAndAvailable(available,
                kidsPlaces,
                adultPlaces,
                amount
                );
    }

    @Override
    public List<Room> findRoomsByRoomParams(RoomParams roomParams) {
        return roomRepository.findAllByTypeIn(
                roomParams.getDateFrom(),
                roomParams.getDateTo(),
                roomParams.getNumbersOfRooms(),
                roomParams.getAdults(),
                roomParams.getChildrens());
    }

    @Override
    public List<Room> findRoomsByRoomParamsWithRoomType(RoomParams roomParams) {
        return roomRepository.findAllByTypeInWithRoomType(
                roomParams.getDateFrom(),
                roomParams.getDateTo(),
                roomParams.getNumbersOfRooms(),
                roomParams.getAdults(),
                roomParams.getChildrens(),
                roomParams.getRoomType());
    }
}
