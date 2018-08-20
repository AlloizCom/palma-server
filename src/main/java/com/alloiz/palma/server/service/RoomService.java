package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Room;
import com.alloiz.palma.server.model.enums.RoomType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomService {
    Room findOneAvailable(Long id);

    List<Room> findAllAvailable();

    Room findOne(Long id);

    List<Room> findAll();

    Room save(Room room);

    Room save(String roomJson,MultipartFile[] multipartFiles);

    Room update(Room room);

    Room update(String roomJson, MultipartFile[] multipartFiles);

    Room update(String roomJson);

    Room addImages(Long roomId, MultipartFile[] multipartFiles);

    Boolean delete(Long id);

    Boolean changeAmount (RoomType roomType, Integer amount);

    Boolean deleteImage(Long roomId, Long imageId);
}
