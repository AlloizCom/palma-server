package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ServiceService {

    Service findOneAvailable(Long id);

    List<Service> findAllAvailable();

    Service findOne(Long id);

    List<Service> findAll();

    Service save(Service service);

    Service save(String serviceJson);

    Service save(String newsJson, MultipartFile multipartFile);

    Service update(String serviceJson, MultipartFile multipartFile);

    Service update(Service service);

    Service update(String serviceJson);

    Boolean delete(Long id);
}
