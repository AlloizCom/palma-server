package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.CallbackCounter;
import com.alloiz.palma.server.repository.CallbackCounterRepository;
import com.alloiz.palma.server.service.CallbackCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static com.alloiz.palma.server.service.utils.Validation.checkId;
import static com.alloiz.palma.server.service.utils.Validation.checkObjectExistsById;

@Service
public class CallbackCounterServiceImpl implements CallbackCounterService {

    @Autowired
    private CallbackCounterRepository callbackCounterRepository;

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public CallbackCounter getCounter(Long id) {
        checkId(id);
        return callbackCounterRepository.findOne(id);
    }

    @Override
    public CallbackCounter update(CallbackCounter counter) {
        checkObjectExistsById(counter.getId(), callbackCounterRepository);
        return callbackCounterRepository.save(getCounter(counter.getId())
                .setNumberOfCallbacks(counter.getNumberOfCallbacks())
                .setAvailable(counter.getAvailable()));
    }

    @Override
    public Boolean delete(Long id) {
        try {
            callbackCounterRepository
                    .delete(checkObjectExistsById(id, callbackCounterRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean createDefaultCounter() {
        if(callbackCounterRepository.findAllByAvailable(true) == null
                || callbackCounterRepository.findAllByAvailable(true).size() == 0){
            CallbackCounter counter = new CallbackCounter();
            counter.setAvailable(true);
            counter.setNumberOfCallbacks(0L);
            callbackCounterRepository.save(counter);
            return true;
        }
        return false;
    }

    @Override
    public CallbackCounter resetCounter(Long id) {
        CallbackCounter counter = update(getCounter(id).setNumberOfCallbacks(0L));
        template.convertAndSend("/callback/not",counter);
        return counter;
    }

    @Override
    public CallbackCounter incrementCounter(Long id) {
        CallbackCounter counter = getCounter(id);
        counter = update(counter.setNumberOfCallbacks(counter.getNumberOfCallbacks()+1));
        template.convertAndSend("/callback/not",counter);
        return counter;
    }
}
