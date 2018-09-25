package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.dto.CallbackByPage;
import com.alloiz.palma.server.dto.CallbackDto;
import com.alloiz.palma.server.model.Callback;
import com.alloiz.palma.server.repository.CallbackRepository;
import com.alloiz.palma.server.service.CallbackCounterService;
import com.alloiz.palma.server.service.CallbackService;
import com.alloiz.palma.server.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;
import static com.alloiz.palma.server.service.utils.Validation.*;
import static java.util.stream.Collectors.toList;

@Service
public class CallbackServiceImpl implements CallbackService {

    @Autowired
    private CallbackRepository callbackRepository;

    @Autowired
    private CallbackCounterService callbackCounterService;

    @Autowired
    private MailService mailService;

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
        mailService.sendCallbackLetterForStuff(callback);
        callbackCounterService.incrementCounter(1L);
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

    @Override
    public List<Callback> findAll(Pageable pageable) {
        List<Callback> callbacks = callbackRepository.findAll(pageable).getContent();
        return callbacks;
    }

    @Override
    public CallbackByPage findAllByAvailable(Pageable pageable) {
        LOGGER.info(">>> Page number:" + pageable.getPageNumber());
        LOGGER.info(">>> Page size:" + pageable.getPageSize());
        List<CallbackDto> callbacks = callbackRepository
                //.findAllByAvailable(true, pageable)
                .findAllByAvailableOrderByDateTimeDesc(true,pageable)
                .getContent()
                .stream()
                .map(callback -> map(callback, CallbackDto.class))
                .collect(toList());
        LOGGER.info("-------------Callback Page---------------");
        callbacks.stream().forEach(n -> LOGGER.info(n.getId()));
        LOGGER.info("-----------------------------------------");
        return new CallbackByPage()
                .setCallbacks(callbacks)
                .setCurrentPage(pageable.getPageNumber())
                .setNumberOfItems(pageable.getPageSize())
                .setNumberOfPages((callbackRepository
                        .countAllByAvailable(true) / pageable.getPageSize()) + 1);
    }
}
