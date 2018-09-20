package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.BookCounter;
import com.alloiz.palma.server.model.Callback;
import com.alloiz.palma.server.repository.CallbackRepository;
import com.alloiz.palma.server.service.BookCounterService;
import com.alloiz.palma.server.service.CallbackService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class CallbackServiceImpl implements CallbackService {

    @Autowired
    private CallbackRepository callbackRepository;

    private static final Logger LOGGER = Logger.getLogger(CallbackServiceImpl.class);

    @Override
    public Callback findOneAvailable(Long id) {
        checkId(id);
        return callbackRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Callback> findAllAvailable() {
        return callbackRepository.findAllByAvailable(true);
    }

    @Override
    public Callback findOne(Long id) {
        checkId(id);
        return callbackRepository.findOne(id);
    }

    @Override
    public List<Callback> findAll() {
        return callbackRepository.findAll();
    }

    @Override
    public Callback save(Callback callback) {
        checkSave(callback);
        LOGGER.info(callback);
        Callback callbackSave = callbackRepository.save(callback
                .setDateTime(Timestamp.valueOf(LocalDateTime.now()))
                .setAvailable(true));

        LOGGER.info("-------------------------Save--------------------------");
        LOGGER.info(callbackSave);
        return callbackSave;
    }

    @Override
    public Callback update(Callback callback) {
        checkObjectExistsById(callback.getId(), callbackRepository);
        return callbackRepository.save(findOne(callback.getId())
                .setName(callback.getName())
                .setMessage(callback.getMessage())
                .setEmail(callback.getEmail())
                .setPhone(callback.getPhone())
                .setAvailable(callback.getAvailable()));
    }

    @Override
    public Boolean delete(Long id) {
        try {
            callbackRepository.delete(checkObjectExistsById(id, callbackRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
