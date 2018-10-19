package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.RoomForSale;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomForSaleService {

    RoomForSale findOneAvailable(Long id);

    List<RoomForSale> findAllAvailable();

    RoomForSale findOne(Long id);

    List<RoomForSale> findAll();

    RoomForSale save(RoomForSale roomForSale);

    RoomForSale save(String roomForSaleJson, MultipartFile multipartFile);

    RoomForSale update(String roomForSaleJson, MultipartFile multipartFile);

    RoomForSale update(RoomForSale roomForSale);

    Boolean delete(Long id);
}
