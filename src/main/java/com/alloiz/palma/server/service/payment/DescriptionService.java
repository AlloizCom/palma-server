package com.alloiz.palma.server.service.payment;

import com.alloiz.palma.server.model.payment.Description;

import java.util.List;


public interface DescriptionService extends CRUDService<Description>
{
    Description findOneAvailable(Long id);

    List<Description> findAllAvailable();
}
