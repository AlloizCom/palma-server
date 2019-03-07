package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.config.DefaultDataCreator;
import com.alloiz.palma.server.model.BookCounter;
import com.alloiz.palma.server.repository.BookCounterRepository;
import com.alloiz.palma.server.service.BookCounterService;
import com.alloiz.palma.server.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alloiz.palma.server.service.utils.Validation.checkId;
import static com.alloiz.palma.server.service.utils.Validation.checkObjectExistsById;

@Service
public class BookCounterServiceImpl implements BookCounterService {

    @Autowired
    private BookCounterRepository bookCounterRepository;

    @Autowired
    private SimpMessagingTemplate template;

    private static final Logger LOGGER
            = Logger.getLogger(BookCounterServiceImpl.class);

    @Override
    public BookCounter getCounter(Long id) {
        checkId(id);
        return bookCounterRepository.findOne(id);
    }

    @Override
    public BookCounter getActiveCounter() {
        List<BookCounter> bookCounters =bookCounterRepository.findAllByAvailable(true);
        if(bookCounters.size() == 1)
            return bookCounters.get(0);

        bookCounters.forEach(bookCounter -> delete(bookCounter.getId()) );
        LOGGER.error("Not single active book counter was found.\nCounters have been deleted and new one was created!!!!");
        createDefaultCounter();

        return getActiveCounter();
    }

    @Override
    public BookCounter update(BookCounter counter) {
        checkObjectExistsById(counter.getId(), bookCounterRepository);
        return bookCounterRepository.save(getCounter(counter.getId())
                .setNumberOfBooking(counter.getNumberOfBooking())
                .setAvailable(counter.getAvailable()));
    }

    @Override
    public Boolean delete(Long id) {
        try {
            bookCounterRepository.delete(checkObjectExistsById(id, bookCounterRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean createDefaultCounter() {
        if(bookCounterRepository.findAllByAvailable(true) == null
                || bookCounterRepository.findAllByAvailable(true).size() == 0){
            BookCounter counter = new BookCounter();
            counter.setAvailable(true);
            counter.setNumberOfBooking(0L);
            bookCounterRepository.save(counter);
            return true;
        }
        return false;
    }

    @Override
    public BookCounter resetCounter(Long id) {
        BookCounter counter = update(getCounter(id).setNumberOfBooking(0L));
        template.convertAndSend("/booking/not",counter);
        return counter;
    }

    @Override
    public BookCounter incrementCounter(Long id) {
        BookCounter counter = getCounter(id);
        counter = update(counter.setNumberOfBooking(counter.getNumberOfBooking()+1));
        template.convertAndSend("/booking/not",counter);
        return counter;
    }
}
