package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.RoomFullDto;
import com.alloiz.palma.server.dto.RoomMiddleDto;
import com.alloiz.palma.server.dto.RoomWithTariff;
import com.alloiz.palma.server.model.Book;
import com.alloiz.palma.server.model.Room;
import com.alloiz.palma.server.model.RoomDescription;
import com.alloiz.palma.server.model.Tariff;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.repository.utils.RoomParams;
import com.alloiz.palma.server.service.BookService;
import com.alloiz.palma.server.service.RoomService;
import com.alloiz.palma.server.service.TariffService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/room")
public class RoomController {

    private static final Logger LOGGER = Logger.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private BookService bookService;

    @GetMapping("/find-all")
    private ResponseEntity<List<RoomFullDto>> findAll() {
        return new ResponseEntity<>(roomService.findAll().stream()
                .map(room -> map(room, RoomFullDto.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available-split")
    private ResponseEntity<List<RoomFullDto>> findAllAvailableSplit() {
        List<Room> rooms = roomService.findAllAvailable();
        List<Room> ret = new ArrayList<>();
        for (Room room: rooms
             ) {
            List<RoomDescription> roomDescriptions = room.getDescriptions();
            roomDescriptions.stream()
                    .filter(roomDescription -> roomDescription.getDescription().length()>251)
                    .forEach(roomDescription -> roomDescription
                            .setDescription(roomDescription.getDescription().substring(0,250).concat(" ...")));
            room.setDescriptions(roomDescriptions);
            ret.add(room);
        }
        return new ResponseEntity<>(ret.stream()
                .map(room -> map(room, RoomFullDto.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<RoomFullDto>> findAllAvailable() {
        return new ResponseEntity<>(roomService.findAllAvailable().stream()
                .map(room -> map(room, RoomFullDto.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available-kids-adult/{kidsPlaces}/{adultPlaces}")
    private ResponseEntity<List<RoomFullDto>> findAllByAdultPlacesAndKidsPlacesAndAvailable(
            @PathVariable Integer kidsPlaces,
            @PathVariable Integer adultPlaces
    ) {
        return new ResponseEntity<>(roomService.findAllByAdultPlacesAndKidsPlacesAndAvailable(
                true,
                kidsPlaces,
                adultPlaces
        ).stream()
                .map(room -> map(room, RoomFullDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/find-by-book-params")
    private ResponseEntity<List<RoomWithTariff>> findRyRoomsParams(@RequestBody RoomParams roomParams){
        LOGGER.info("-----------------FIND ROOM BY PARAMS---------------------");
        LOGGER.info(roomParams);
        return new ResponseEntity<>(roomService.findRoomsByRoomParams(roomParams)
                .stream()
                    .map(room -> map(room, RoomWithTariff.class)
                        .setPrice(tariffService.findByRoomTypeAndDateNow(room.getType()).getPrice())
                    .setImages(room.getImages().subList(0,1)))
                .collect(Collectors.toList()), HttpStatus.OK);

    }

    @PostMapping("/find-by-book-params-with-room-type")
    private ResponseEntity<List<RoomWithTariff>> findRyRoomsParamsWithRoomType(@RequestBody RoomParams roomParams){
        LOGGER.info("-----------------FIND ROOM BY PARAMS WITH ROOM TYPE---------------------");
        LOGGER.info(roomParams);
        return new ResponseEntity<>(roomService.findRoomsByRoomParamsWithRoomType(roomParams)
                .stream()
                .map(room -> map(room, RoomWithTariff.class)
                        .setPrice(tariffService.findByRoomTypeAndDateNow(room.getType()).getPrice()))
                .collect(Collectors.toList()), HttpStatus.OK);

    }

//    @GetMapping("/find-all-available-kids-adult-amount/{kidsPlaces}/{adultPlaces}/{amount}")
//    private ResponseEntity<List<RoomFullDto>> findAllByAdultPlacesAndKidsPlacesAndAmountAndAvailable(
//            @PathVariable Integer kidsPlaces,
//            @PathVariable Integer adultPlaces,
//            @PathVariable Integer amount
//    ) {
//        return new ResponseEntity<>(roomService.findAllByAdultPlacesAndKidsPlacesAndAmountAndAvailable(
//                true,
//                kidsPlaces,
//                adultPlaces,
//                amount
//        ).stream()
//                .map(room -> map(room, RoomFullDto.class))
//                .collect(Collectors.toList()), HttpStatus.OK);
//    }

    /**
     *
     * @param kidsPlaces
     * @param adultPlaces
     * @param amount
     * @return list of room
     */
    @GetMapping("/find-all-available-kids-adult-amount/{kidsPlaces}/{adultPlaces}/{amount}")
    private ResponseEntity<List<RoomWithTariff>> findAllByAdultPlacesAndKidsPlacesAndAmountAndAvailable(
            @PathVariable Integer kidsPlaces,
            @PathVariable Integer adultPlaces,
            @PathVariable Integer amount
    ) {
        return new ResponseEntity<>(roomService.findAllAvailable().stream()
                .filter(room -> room.getKidsPlaces()*amount>kidsPlaces)
                .filter(room -> room.getAdultPlaces()*amount>adultPlaces)
                .filter(room -> room.getAmount()>amount)
                .map(room -> map(room, RoomWithTariff.class)
                        .setPrice(tariffService.findByRoomTypeAndDateNow(room.getType())
                                .getPrice())).collect(Collectors.toList()),HttpStatus.OK);
    }

    @GetMapping("/find-all-available-kids-adult-amount-date/{kidsPlaces}/{adultPlaces}/{amount}/{dateFrom}/{dateTo}")
    private ResponseEntity<List<RoomWithTariff>> findAllByAdultPlacesAndKidsPlacesAndAmountAndAvailableAndDate(
            @PathVariable Integer kidsPlaces,
            @PathVariable Integer adultPlaces,
            @PathVariable Integer amount,
            @PathVariable String dateFrom,
            @PathVariable String dateTo
    ) {
        Timestamp dateFromTS = Timestamp.valueOf(dateFrom);
        Timestamp dateToTS = Timestamp.valueOf(dateTo);
        List<Room> rooms = new ArrayList<>();
        List<Book> books = bookService.findAllAvailable();
        for (Book book: books
             ) {
            if((book.getDateIn().after(dateToTS) && book.getDateIn().after(dateFromTS))
                    || (book.getDateOut().before(dateFromTS) && book.getDateOut().before(dateToTS))
                    ){
                rooms.addAll(roomService.findAllAvailableAndType(book.getRoomType()));
            }
        }
        return new ResponseEntity<>(rooms.stream()
                .filter(room -> room.getKidsPlaces()*amount>kidsPlaces)
                .filter(room -> room.getAdultPlaces()*amount>adultPlaces)
                .filter(room -> room.getAmount()>amount)
                .map(room -> map(room, RoomWithTariff.class)
                        .setPrice(tariffService.findByRoomTypeAndDateNow(room.getType())
                                .getPrice())).collect(Collectors.toList()),HttpStatus.OK);
    }


    @GetMapping("/find-all-only-with-images")
    private ResponseEntity<List<RoomMiddleDto>> findAllOnlyWithImages() {
        return new ResponseEntity<>(roomService
                .findAll()
                .stream()
                .map(room -> map(room, RoomMiddleDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available-only-with-images")
    private ResponseEntity<List<RoomMiddleDto>> findAllAvailableOnlyWithImages() {
        return new ResponseEntity<>(roomService
                .findAllAvailable()
                .stream()
                .map(room -> map(room, RoomMiddleDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }


    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<RoomFullDto> findOneAvailale(@PathVariable Long id) {
        return new ResponseEntity<>(map(roomService.findOneAvailable(id), RoomFullDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<RoomFullDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(roomService.findOne(id), RoomFullDto.class), HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return one room with price
     */
    @GetMapping("/find-one-with-price/{id}")
    private ResponseEntity<RoomWithTariff> findOneWithPrice(@PathVariable Long id) {
        Room room = roomService.findOne(id);
        Tariff tariffs = tariffService.findByRoomTypeAndDateNow(room.getType());
        return new ResponseEntity<>(map(room, RoomWithTariff.class)
                .setPrice(tariffs.getPrice()), HttpStatus.OK);
    }

    @PostMapping("/save")
    private ResponseEntity<RoomFullDto> save(@RequestParam String roomJson,
                                             @RequestParam(required = false) MultipartFile[] multipartFiles) {
        LOGGER.info("---------------------------Room---------------------------");
        LOGGER.info(roomJson);
        LOGGER.info(multipartFiles.length);
        LOGGER.info("---------------------------Room---------------------------");
        return ResponseEntity.ok(map(roomService.save(roomJson, multipartFiles), RoomFullDto.class));
//        return ResponseEntity.ok(map(workerService.save(workerJson, multipartFile), WorkerFullDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<RoomFullDto> update(@RequestParam String roomJson, @RequestParam(required = false) MultipartFile[] multipartFiles) {
        LOGGER.info("---------------------------Room---------------------------");
        LOGGER.info(roomJson);
        LOGGER.info("Img: " + multipartFiles.length);
        LOGGER.info("---------------------------Room---------------------------");
        if (multipartFiles != null && multipartFiles.length != 0&&!multipartFiles[0].isEmpty()) {
            return ResponseEntity.ok(map(roomService.update(roomJson, multipartFiles), RoomFullDto.class));
        } else {
            return ResponseEntity.ok(map(roomService.update(roomJson), RoomFullDto.class));
        }
    }

    @PostMapping("/add-images")
    private ResponseEntity<RoomFullDto> addImages(@RequestParam MultipartFile[] multipartFiles, @RequestParam Long roomId) {
        return ResponseEntity.ok(map(roomService.addImages(roomId, multipartFiles), RoomFullDto.class));
    }

    @PostMapping("/add-images/{roomId}")
    private ResponseEntity<RoomFullDto> addImagesPV(@RequestParam MultipartFile[] multipartFiles, @PathVariable Long roomId) {
        return ResponseEntity.ok(map(roomService.addImages(roomId, multipartFiles), RoomFullDto.class));
    }

//    @PostMapping("/delete-image")
//    private ResponseEntity<Boolean> deleteImage(@RequestParam Long roomId,@RequestParam Long imageId) {
//        return ResponseEntity.ok(roomService.deleteImage(roomId, imageId));
//    }

    @GetMapping("/change-amount/{roomType}/amount/{amount}")
    private ResponseEntity<Boolean> changeAmount(@PathVariable RoomType roomType,
                                                 @PathVariable Integer amount) {
        return ResponseEntity.ok(roomService.changeAmount(roomType, amount));
    }

    @DeleteMapping("/delete-image/{roomId}/image/{imageId}")
    private ResponseEntity<Boolean> deleteImage(
            @PathVariable Long roomId,
            @PathVariable Long imageId) {
        return ResponseEntity.ok(roomService.deleteImage(roomId, imageId));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.delete(id));
    }

    @GetMapping("/find-room-with-price")
    private ResponseEntity<List<RoomWithTariff>> findAllRoomWithPrice() {
        return new ResponseEntity<>(roomService.findAllAvailable().stream()
                .map(room -> map(room, RoomWithTariff.class)
                        .setPrice(tariffService.findByRoomTypeAndDateNow(room.getType())
                                .getPrice())).collect(Collectors.toList()), HttpStatus.OK);
    }
}