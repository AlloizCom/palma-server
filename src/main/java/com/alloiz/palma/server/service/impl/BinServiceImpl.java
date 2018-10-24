package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Amenity;
import com.alloiz.palma.server.model.Bin;
import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.Schedule;
import com.alloiz.palma.server.model.enums.Language;
import com.alloiz.palma.server.repository.BinRepository;
import com.alloiz.palma.server.service.BinService;
import com.alloiz.palma.server.service.BookCounterService;
import com.alloiz.palma.server.service.ScheduleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.service.utils.GenerateUuid.generateUuid;
import static com.alloiz.palma.server.service.utils.Validation.*;

@Service
public class BinServiceImpl implements BinService {

    private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class);

    @Autowired
    private BinRepository binRepository;

    @Autowired
    private BookCounterService bookCounterService;

    @Autowired
    private ScheduleService scheduleService;

    @Override
    public Bin findOneAvailable(Long id) {
        checkId(id);
        return binRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Bin> findAllAvailable() {
        return binRepository.findAllByAvailable(true);
    }

    @Override
    public Bin findOne(Long id) {
        checkId(id);
        return binRepository.findOne(id);
    }

    @Override
    public List<Bin> findAll() {
        return binRepository.findAll();
    }

    @Override
    public Bin save(Bin bin, Language language) {
        checkSave(bin);
        bookCounterService.incrementCounter(1L);
        LOGGER.info("Bin service:" + bin);
        Integer roomSum = 0;
        Integer amenitiesSum = 0;
        if(isNullOrEmpty(bin.getBooks())){
            for (Book book: bin.getBooks()
                 ) {
                List<Schedule> schedules = scheduleService.getRoomBetweenDates(bin.getDateIn(),bin.getDateOut())
                        .stream()
                        .filter(schedule -> schedule.getRoom().getId().equals(book.getRoom().getId()))
                        .collect(Collectors.toList());
                for (Schedule schedule: schedules
                     ) {
                    roomSum += schedule.getPrice();
                }
                if (isNullOrEmpty(book.getAdditionalAmenities())){
                    for (Amenity amenity: book.getAdditionalAmenities()
                         ) {
                        amenitiesSum += amenity.getPrice();
                    }
                }
            }
        }
        bin.setPriceRoom(roomSum);
        bin.setPriceAdditionalAmenities(amenitiesSum);
        bin.setPriceTotal(roomSum + amenitiesSum);
        bin.setBookingDay(Timestamp.valueOf(LocalDateTime.now()));
//        mailService.sendBookMailForStuffAndUser(book,language);
        return binRepository.save(generateUuid(bin
                .setAvailable(true)
        ));
    }

    @Override
    public Bin update(Bin bin) {
        checkObjectExistsById(bin.getId(), binRepository);
        return binRepository.save(findOne(bin.getId())
                .setAdults(bin.getAdults())
                .setBookingDay(bin.getBookingDay())
                .setBooks(bin.getBooks())
                .setDateIn(bin.getDateIn())
                .setDateOut(bin.getDateOut())
                .setEmail(bin.getEmail())
                .setFirstName(bin.getFirstName())
                .setKids(bin.getKids())
                .setLastName(bin.getLastName())
                .setOrderStatus(bin.getOrderStatus())
                .setPhoneNumber(bin.getPhoneNumber())
                .setUuid(bin.getUuid())
                .setAvailable(bin.getAvailable())
        );
    }

    @Override
    public Bin findByUuid(String uuid) {
        checkString(uuid);
        LOGGER.info(">>> UUID: " + uuid);
        return binRepository.findByUuid(uuid);
    }

    @Override
    public Boolean delete(Long id) {
        try {
            binRepository.delete(checkObjectExistsById(id, binRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }




}
