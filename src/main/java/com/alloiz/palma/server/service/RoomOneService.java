package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.RoomOne;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomOneService {

    RoomOne findOneAvailable(Long id);

    List<RoomOne> findAllAvailable();

    RoomOne findOne(Long id);

    List<RoomOne> findAll();

    RoomOne save(RoomOne roomOne);

    RoomOne save(String roomOneJson,MultipartFile[] multipartFiles);

    RoomOne update(RoomOne roomOne);

    RoomOne update(String roomOneJson, MultipartFile[] multipartFiles);

    RoomOne update(String roomOneJson);

    Boolean delete(Long id);
}
