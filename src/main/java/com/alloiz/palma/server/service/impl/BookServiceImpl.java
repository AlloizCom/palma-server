package com.alloiz.palma.server.service.impl;

import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.Room;
import com.alloiz.palma.server.model.enums.OrderStatus;
import com.alloiz.palma.server.repository.BookRepository;
import com.alloiz.palma.server.repository.RoomRepository;
import com.alloiz.palma.server.service.BookService;
import com.alloiz.palma.server.service.RoomService;
import com.alloiz.palma.server.service.ScheduleService;
import com.alloiz.palma.server.service.exceptions.NotEnoughFreePlacesException;
import com.alloiz.palma.server.service.exceptions.RoomTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alloiz.palma.server.service.utils.GenerateUuid.generateUuid;
import static com.alloiz.palma.server.service.utils.Validation.*;
import static java.util.stream.Collectors.toList;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ScheduleService scheduleService;


    @Override
    public Book findOneAvailable(Long id) {
        checkId(id);
        return bookRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Book> findAllAvailable() {
        return bookRepository.findAllByAvailable(true);
    }

    @Override
    public Book findOne(Long id) {
        checkId(id);
        return bookRepository.findOne(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
//        checkSave(book);
//        List<Room> rooms = roomRepository.findAllByAvailableAndType(true, book.getRoomType());
//        try {
//            Room resRoom = rooms.stream()
//                    .filter(room -> room.getType().equals(book.getRoomType()))
//                    .map(room -> room.setAmount(room.getAmount() - book.getAmountOfRooms()))
//                    .findFirst().orElseThrow(Exception::new);
//            if(resRoom.getAmount()<0){
//                throw new Exception();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (Room room : rooms) {
//            if (room.getType().equals(book.getRoomType())) {
//                if (room.getAmount() >= book.getAmountOfRooms()) {
//                    room.setAmount(room.getAmount() - book.getAmountOfRooms());
//                }
//            }
//        }


//        checkSave(book);
//        List<Room> rooms = roomRepository.findAllByAvailableAndType(true, book.getRoomType());
//        for (Room room : rooms) {
//            if (room.getType().equals(book.getRoomType())) {
//                if (room.getAmount() >= book.getAmountOfRooms()) {
//                    room.setAmount(room.getAmount() - book.getAmountOfRooms());
//                    roomService.changeAmount(room.getType(), book.getAmountOfRooms());
//                } else {
//                    throw new NotEnoughFreePlacesException("Not Enough Free Places !");
//                }
//            } else {
//                throw new RoomTypeNotFoundException("Room Type Not Found !");
//            }
//        }

        checkSave(book);


        return bookRepository.save(generateUuid(book.setAvailable(true)));
    }

    @Override
    public Book update(Book book) {
        checkObjectExistsById(book.getId(), bookRepository);
        return bookRepository.save(findOne(book.getId())
                .setOrderStatus(book.getOrderStatus())
                .setAvailable(book.getAvailable()));
    }

    @Override
    public Boolean delete(Long id) {
        try {
            bookRepository.delete(checkObjectExistsById(id, bookRepository));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Book findByUuid(String uuid) {
        checkString(uuid);
        return bookRepository.findByUuid(uuid);
    }
}
