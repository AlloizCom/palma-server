package com.alloiz.palma.server.controller.payment;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

import com.alloiz.palma.server.dto.payment.RoomFullDto;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.payment.Description;
import com.alloiz.palma.server.model.payment.Language;
import com.alloiz.palma.server.model.payment.Room;
import com.alloiz.palma.server.service.RoomService;
import com.alloiz.palma.server.service.payment.PaymentRoomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/payment_room")
public class PaymentRoomController
{

	private static final Logger LOGGER = Logger.getLogger(PaymentRoomController.class);


	@Autowired
	private PaymentRoomService paymentRoomService;

	@Autowired
	private RoomService roomService;

	@GetMapping("/find-all")
	private ResponseEntity<List<RoomFullDto>> findAll()
	{
		return ResponseEntity.ok(paymentRoomService.findAll().stream()
				.map(room -> map(room, RoomFullDto.class)).collect(Collectors.toList()));
	}

	@GetMapping("/find-one/{id}")
	private ResponseEntity<RoomFullDto> findOne(@PathVariable Long id)
	{
		return ResponseEntity.ok(map(paymentRoomService.findOne(id), RoomFullDto.class));
	}

	@GetMapping("/find-all-available")
	private ResponseEntity<List<RoomFullDto>> findAllAvailable()
	{
		return ResponseEntity.ok(paymentRoomService.findAllAvailable().stream()
				.map(room -> map(room, RoomFullDto.class)).collect(Collectors.toList()));
	}

	@GetMapping("/find-all-by-type/{type}")
	private ResponseEntity<List<RoomFullDto>> findAllByType(@PathVariable RoomType type)
	{
		com.alloiz.palma.server.model.Room roomDefault = roomService.findByType(type);
		return ResponseEntity.ok(paymentRoomService.findAllByType(type).stream()
				.map(room -> map(room, RoomFullDto.class).setDescriptions(roomDefault.getDescriptions().stream()
						.map(roomDescription -> new Description()
								.setLanguage(new Language().setLanguagesName(roomDescription.getLanguage().toString()))
								.setText(roomDescription.getDescription())
						).collect(Collectors.toList())))
				.collect(Collectors.toList()));
	}

	@GetMapping("/find-all-by-type-with-dates-and-places/{type}/{dateFrom}/{dateTo}/{places}")
	private ResponseEntity<List<RoomFullDto>> findAllByTypeAndDatesAndPlaces(@PathVariable RoomType type,
															@PathVariable Timestamp dateFrom,
															@PathVariable Timestamp dateTo,
															@PathVariable Integer places)
	{
		com.alloiz.palma.server.model.Room roomDefault = roomService.findByType(type);
		List<RoomFullDto> rooms =paymentRoomService.findAllByTypeWithDatesAndPlaces(type,dateFrom,dateTo,places)
				.stream()
				.map(room ->  map(room, RoomFullDto.class).setDescriptions(roomDefault.getDescriptions().stream()
						.map(roomDescription -> new Description()
								.setLanguage(new Language().setLanguagesName(roomDescription.getLanguage().toString()))
								.setText(roomDescription.getDescription())
						).collect(Collectors.toList())))
				.collect(Collectors.toList());
		LOGGER.info("_________________________________");
		LOGGER.info("FIND BY TYPE AND DATES AND PLACES");
        LOGGER.info("_________________________________");
        return ResponseEntity.ok(rooms);
	}

	@GetMapping("/find-all-by-dates-and-places/{dateFrom}/{dateTo}/{places}")
	private ResponseEntity<List<RoomFullDto>> findAllBydatesAndPlaces(@PathVariable Timestamp dateFrom,
															@PathVariable Timestamp dateTo,
															@PathVariable Integer places)
	{

		List<RoomFullDto> rooms =paymentRoomService.findAllByDatesAndPlaces(dateFrom,dateTo,places)
				.stream()
				.map(room ->  map(room, RoomFullDto.class).setDescriptions(roomService.findByType(room.getRoomType())
						.getDescriptions().stream()
						.map(roomDescription -> new Description()
								.setLanguage(new Language().setLanguagesName(roomDescription.getLanguage().toString()))
								.setText(roomDescription.getDescription())
						).collect(Collectors.toList())))
				.collect(Collectors.toList());

		return ResponseEntity.ok(rooms);
	}


	@PostMapping("/save")
	private ResponseEntity<RoomFullDto> save(@RequestBody Room room)
	{
		LOGGER.info("---------------------------Room---------------------------");
		LOGGER.info(room);
		LOGGER.info("---------------------------Room---------------------------");
		return ResponseEntity.ok(map(paymentRoomService.save(room), RoomFullDto.class));
	}

	@PostMapping("/update")
	private ResponseEntity<RoomFullDto> update(@RequestBody Room room)
	{
		LOGGER.info("---------------------------Room---------------------------");
		LOGGER.info(room);
		LOGGER.info("---------------------------Room---------------------------");

		return ResponseEntity.ok(map(paymentRoomService.update(room), RoomFullDto.class));

	}
	@PostMapping("/update-array")
	private ResponseEntity<List<RoomFullDto>> updateList(@RequestBody List<Room> rooms)
	{
		LOGGER.info("---------------------------Update RoomArray ---------------------------");
		rooms.forEach(LOGGER::info);
		LOGGER.info("---------------------------Update RoomArray ---------------------------");

		List<RoomFullDto> resultRooms =rooms.stream().peek(room ->  map(paymentRoomService.update(room), RoomFullDto.class))
				.map(room ->  map(room, RoomFullDto.class).setDescriptions(roomService.findByType(room.getRoomType())
						.getDescriptions().stream()
						.map(roomDescription -> new Description()
								.setLanguage(new Language().setLanguagesName(roomDescription.getLanguage().toString()))
								.setText(roomDescription.getDescription())
						).collect(Collectors.toList())))
				.collect(Collectors.toList());
		return ResponseEntity.ok(resultRooms);

	}

	@DeleteMapping("/delete/{id}")
	private ResponseEntity<Boolean> delete(@PathVariable Long id)
	{
		return ResponseEntity.ok(paymentRoomService.delete(id));
	}

}
