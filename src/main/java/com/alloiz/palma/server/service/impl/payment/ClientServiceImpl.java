package com.alloiz.palma.server.service.impl.payment;

import com.alloiz.palma.server.model.payment.Client;
import com.alloiz.palma.server.repository.payment.ClientRepository;
import com.alloiz.palma.server.service.payment.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class ClientServiceImpl implements ClientService {


    private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class);
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        checkSave(client);
        client.setAvailable(true);
        LOGGER.info(client);
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        checkObjectExistsById(client.getId(), clientRepository);
        LOGGER.info(client);
        return clientRepository.save(findOne(client.getId())
                .setEmail(client.getEmail())
                .setFirstName(client.getFirstName())
                .setLastName(client.getLastName())
                .setPhoneNumber(client.getPhoneNumber())
                .setThirdName(client.getThirdName())
                .setAvailable(client.getAvailable()));
    }

    @Override
    public Client findOne(Long id) {
        checkId(id);
        return clientRepository.findOne(id);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findOneAvailable(Long id) {
        checkId(id);
        return clientRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Client> findAllAvailable() {
        return clientRepository.findAllByAvailable(true);
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER.info(">>> " + id);
        checkId(id);
        try {
            clientRepository.delete(checkObjectExistsById(id, clientRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Client obj) {
        Long id = obj.getId();
        LOGGER.info(">>> " + id);
        checkId(id);
        try {
            clientRepository.delete(checkObjectExistsById(id, clientRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
