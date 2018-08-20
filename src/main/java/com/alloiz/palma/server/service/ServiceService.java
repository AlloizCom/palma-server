package com.alloiz.palma.server.service;

import com.alloiz.palma.server.model.Service;

import java.util.List;

public interface ServiceService {

    Service findOneAvailable(Long id);

    List<Service> findAllAvailable();

    Service findOne(Long id);

    List<Service> findAll();

    Service save(Service service);

    Service save(String serviceJson);

    Service update(Service service);

    Service update(String serviceJson);

    Boolean delete(Long id);
}
