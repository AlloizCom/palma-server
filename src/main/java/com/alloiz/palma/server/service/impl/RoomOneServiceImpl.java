package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.repository.ImageRepository;
import com.alloiz.palma.server.repository.RoomOneRepository;
import com.alloiz.palma.server.service.ImageService;
import com.alloiz.palma.server.service.RoomService;
import com.alloiz.palma.server.service.utils.FileBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomOneServiceImpl {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Autowired
    private ImageService imageService;

    @Autowired
    private RoomOneRepository roomOneRepository;

    private static final Logger LOGGER = Logger.getLogger(RoomServiceImpl.class);




}
