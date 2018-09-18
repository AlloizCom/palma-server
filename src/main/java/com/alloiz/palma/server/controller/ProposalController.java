package com.alloiz.palma.server.controller;

import com.alloiz.palma.server.dto.ProposalFullDto;
import com.alloiz.palma.server.service.ProposalService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

/**
 *
 * Controller for proposal
 *
 */
@RestController
@RequestMapping("/proposal")
public class ProposalController {

    private static final Logger LOGGER = Logger.getLogger(ProposalController.class);

    @Autowired
    private ProposalService proposalService;

    @GetMapping("/find-all")
    private ResponseEntity<List<ProposalFullDto>> findAll() {
        return new ResponseEntity<>(proposalService.findAll()
                .stream()
                .map(news -> map(news, ProposalFullDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-all-available")
    private ResponseEntity<List<ProposalFullDto>> findAllAvailable() {
        return new ResponseEntity<>(proposalService.findAllAvailable()
                .stream()
                .map(news -> map(news, ProposalFullDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one-available/{id}")
    private ResponseEntity<ProposalFullDto> findOneAvailale(@PathVariable Long id) {
        return new ResponseEntity<>(map(proposalService.findOneAvailable(id), ProposalFullDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<ProposalFullDto> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(map(proposalService.findOne(id), ProposalFullDto.class), HttpStatus.OK);
    }

    /**
     *
     * @param proposalJson
     * @param multipartFile
     * @return
     */
    @PostMapping("/save")
    private ResponseEntity<ProposalFullDto> save(@RequestParam String proposalJson,
                                                @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Proposal---------------------------");
        LOGGER.info(proposalJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Proposal---------------------------");
        return ResponseEntity
                .ok(map(proposalService.save(proposalJson, multipartFile), ProposalFullDto.class));
    }

    /**
     *
     * @param proposalJson
     * @param multipartFile
     * @return
     */
    @PostMapping("/update")
    private ResponseEntity<ProposalFullDto> update(@RequestParam String proposalJson,
                                                  @RequestParam(required = false) MultipartFile multipartFile) {
        LOGGER.info("---------------------------Proposal---------------------------");
        LOGGER.info(proposalJson);
        LOGGER.info(multipartFile);
        LOGGER.info("---------------------------Proposal---------------------------");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            LOGGER.info("multipart file not null");
            return ResponseEntity
                    .ok(map(proposalService.update(proposalJson, multipartFile), ProposalFullDto.class));
        } else {
            LOGGER.info("multipart file is null!");
            return ResponseEntity
                    .ok(map(proposalService.update(proposalJson), ProposalFullDto.class));
        }
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity
                .ok(proposalService.delete(id));
    }
}
