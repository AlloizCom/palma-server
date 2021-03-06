package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Service;
import com.alloiz.palma.server.repository.ServiceRepository;
import com.alloiz.palma.server.service.ServiceService;
import com.alloiz.palma.server.service.utils.FileBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.alloiz.palma.server.config.mapper.JsonMapper.json;
import static com.alloiz.palma.server.service.utils.Validation.*;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private static final Logger LOGGER = Logger.getLogger(ServiceServiceImpl.class);


    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Override
    public Service findOneAvailable(Long id) {
        checkId(id);
        return serviceRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Service> findAllAvailable() {
        return serviceRepository.findAllByAvailable(true);
    }

    @Override
    public Service findOne(Long id) {
        checkId(id);
        return serviceRepository.findOne(id);
    }

    @Override
    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Service save(Service service) {
        checkSave(service);
        return serviceRepository.save(service);
    }

    @Override
    public Service save(String serviceJson) {
        checkJson(serviceJson);
        Service service = json(serviceJson, Service.class);
        service.getServiceDescriptions()
                .stream()
                .forEach(serviceDescription -> serviceDescription.setAvailable(true));
        return serviceRepository.save(service);
    }

//    @Override
//    public Service save(String serviceJson, MultipartFile multipartFile) {
//        checkJson(serviceJson);
//        Service service = json(serviceJson, Service.class);
//        LOGGER.info("----SERVICE--SAVE----");
//        LOGGER.info(service);
//            service.getServiceDescriptions()
//                    .stream()
//                    .forEach(serviceDescription -> serviceDescription.setAvailable(true));
//        if (multipartFile != null && !multipartFile.isEmpty()) {
//            service.setPicturePath(fileBuilder.saveFile(multipartFile));
//        }
//        return save(service);
//    }

    @Override
    public Service save(String serviceJson, MultipartFile multipartFile) {
        checkJson(serviceJson);
        Service service = json(serviceJson, Service.class);
        LOGGER.info("----SERVICE--SAVE----");
        LOGGER.info(service);
        service.getServiceDescriptions()
                .stream()
                .forEach(serviceDescription -> serviceDescription.setAvailable(true));
        if (multipartFile != null && !multipartFile.isEmpty()) {
            service.setPicturePath(fileBuilder.saveFile(multipartFile));
        }
        return save(service);
    }

    @Override
    public Service update(String serviceJson, MultipartFile multipartFile) {
        checkJson(serviceJson);
        Service service = json(serviceJson, Service.class);
        checkObjectExistsById(service.getId(), serviceRepository);
        if (multipartFile != null && !multipartFile.isEmpty())
            service.setPicturePath(fileBuilder.saveFile(multipartFile));
        return save(service.setName(service.getName()))
                .setAvailable(service.getAvailable()
                );
    }

    @Override
    public Service update(Service service) {
        checkObjectExistsById(service.getId(), serviceRepository);
        return serviceRepository.save(
                findOne(service.getId())
                        .setName(service.getName())
                        .setServiceDescriptions(service.getServiceDescriptions())
                        .setAvailable(service.getAvailable())
                        .setShowOnTop(service.getShowOnTop())
        );
    }

    @Override
    public Service update(String serviceJson) {
        checkJson(serviceJson);
        Service service = json(serviceJson, Service.class);
        return update(service);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            serviceRepository.delete(checkObjectExistsById(id, serviceRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
