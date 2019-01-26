package com.alloiz.palma.server.controller.payment;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

import com.alloiz.palma.server.dto.payment.RoomFullDto;
import com.alloiz.palma.server.model.enums.RoomType;
import com.alloiz.palma.server.model.payment.Room;
import com.alloiz.palma.server.service.payment.RoomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController("payment/room")
public class RoomController
{

	private static final Logger LOGGER = Logger.getLogger(RoomController.class);


	@Autowired
	private RoomService roomService;

	@GetMapping("/find-all")
	private ResponseEntity<List<RoomFullDto>> findAll()
	{
		return ResponseEntity.ok(roomService.findAll().stream()
				.map(room -> map(room, RoomFullDto.class)).collect(Collectors.toList()));
	}

	@GetMapping("/find-one/{id}")
	private ResponseEntity<RoomFullDto> findOne(@PathVariable Long id)
	{
		return ResponseEntity.ok(map(roomService.findOne(id), RoomFullDto.class));
	}

	@GetMapping("/find-all-available")
	private ResponseEntity<List<RoomFullDto>> findAllAvailable()
	{
		return ResponseEntity.ok(roomService.findAllAvailable().stream()
				.map(room -> map(room, RoomFullDto.class)).collect(Collectors.toList()));
	}

	@GetMapping("/find-all-by-type/{type}")
	private ResponseEntity<List<RoomFullDto>> findAllByType(@PathVariable RoomType type)
	{
		return ResponseEntity.ok(roomService.findAllByType(type).stream()
				.map(room -> map(room, RoomFullDto.class)).collect(Collectors.toList()));
	}

	@PostMapping("/save")
	private ResponseEntity<RoomFullDto> save(@RequestBody Room room)
	{
		LOGGER.info("---------------------------Room---------------------------");
		LOGGER.info(room);
		LOGGER.info("---------------------------Room---------------------------");
		return ResponseEntity.ok(map(roomService.save(room), RoomFullDto.class));
	}

	@PostMapping("/update")
	private ResponseEntity<RoomFullDto> update(@RequestBody Room room)
	{
		LOGGER.info("---------------------------Room---------------------------");
		LOGGER.info(room);
		LOGGER.info("---------------------------Room---------------------------");

		return ResponseEntity.ok(map(roomService.update(room), RoomFullDto.class));

	}

	@DeleteMapping("/delete/{id}")
	private ResponseEntity<Boolean> delete(@PathVariable Long id)
	{
		return ResponseEntity.ok(roomService.delete(id));
	}

}
