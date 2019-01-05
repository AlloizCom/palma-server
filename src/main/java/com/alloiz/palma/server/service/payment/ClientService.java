package com.alloiz.palma.server.service.payment;

import com.alloiz.palma.server.model.payment.Client;

import java.util.List;


public interface ClientService extends CRUDService<Client>
{
    Client findOneAvailable(Long id);

    List<Client> findAllAvailable();
}
