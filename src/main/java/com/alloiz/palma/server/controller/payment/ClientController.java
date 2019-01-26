package com.alloiz.palma.server.controller.payment;

import com.alloiz.palma.server.dto.payment.ClientShortDto;
import com.alloiz.palma.server.model.payment.Client;
import com.alloiz.palma.server.service.payment.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/payment_client")
public class ClientController {

    private static final Logger LOGGER = Logger.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping("/find-all")
    private ResponseEntity<List<ClientShortDto>> findAll() {
        return ResponseEntity.ok(clientService.findAll().stream()
                .map(client -> map(client, ClientShortDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<ClientShortDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(map(clientService.findOne(id), ClientShortDto.class));
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<ClientShortDto>> findAllAvailable() {
        return ResponseEntity.ok(clientService.findAllAvailable().stream()
                .map(client -> map(client, ClientShortDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<ClientShortDto> findOneAvailable(@PathVariable Long id) {
        return ResponseEntity.ok(map(clientService.findOneAvailable(id), ClientShortDto.class));
    }

    @PostMapping("/save")
    private ResponseEntity<ClientShortDto> save(@RequestBody Client client) {
        LOGGER.info("---------------------------Client---------------------------");
        LOGGER.info(client);
        LOGGER.info("---------------------------Client---------------------------");
        return ResponseEntity.ok(map(clientService.save(client), ClientShortDto.class));
    }

    @PostMapping("/update")
    private ResponseEntity<ClientShortDto> update(@RequestBody Client client) {
        LOGGER.info("---------------------------Client---------------------------");
        LOGGER.info(client);
        LOGGER.info("---------------------------Client---------------------------");

        return ResponseEntity.ok(map(clientService.update(client), ClientShortDto.class));

    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.delete(id));
    }

    @DeleteMapping("/delete")
    private ResponseEntity<Boolean> delete(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.delete(client));
    }

}
