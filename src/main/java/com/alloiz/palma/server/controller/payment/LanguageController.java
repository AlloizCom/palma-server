package com.alloiz.palma.server.controller.payment;

import static com.alloiz.palma.server.dto.utils.builder.Builder.map;

import com.alloiz.palma.server.dto.payment.LanguageFullDto;
import com.alloiz.palma.server.model.payment.Language;
import com.alloiz.palma.server.service.payment.LanguageService;
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


@RestController("/payment/language")
public class LanguageController
{

	private static final Logger LOGGER = Logger.getLogger(LanguageController.class);

	@Autowired
	private LanguageService languageService;

	@GetMapping("/find-all")
	private ResponseEntity<List<LanguageFullDto>> findAll()
	{
		return ResponseEntity.ok(languageService.findAll().stream()
				.map(language -> map(language, LanguageFullDto.class)).collect(Collectors.toList()));
	}

	@GetMapping("/find-one/{id}")
	private ResponseEntity<LanguageFullDto> findOne(@PathVariable Long id)
	{
		return ResponseEntity.ok(map(languageService.findOne(id), LanguageFullDto.class));
	}

	@GetMapping("/find-all-available")
	private ResponseEntity<List<LanguageFullDto>> findAllAvailable()
	{
		return ResponseEntity.ok(languageService.findAllAvailable().stream()
				.map(language -> map(language, LanguageFullDto.class)).collect(Collectors.toList()));
	}

	@GetMapping("/find-one-available/{id}")
	private ResponseEntity<LanguageFullDto> findOneAvailable(@PathVariable Long id)
	{
		return ResponseEntity.ok(map(languageService.findOneAvailable(id), LanguageFullDto.class));
	}

	@PostMapping("/save")
	private ResponseEntity<LanguageFullDto> save(@RequestBody Language language)
	{
		LOGGER.info("---------------------------Language---------------------------");
		LOGGER.info(language);
		LOGGER.info("---------------------------Language---------------------------");
		return ResponseEntity.ok(map(languageService.save(language), LanguageFullDto.class));
	}

	@PostMapping("/update")
	private ResponseEntity<LanguageFullDto> update(@RequestBody Language language)
	{
		LOGGER.info("---------------------------Language---------------------------");
		LOGGER.info(language);
		LOGGER.info("---------------------------Language---------------------------");

		return ResponseEntity.ok(map(languageService.update(language), LanguageFullDto.class));

	}

	@DeleteMapping("/delete/{id}")
	private ResponseEntity<Boolean> delete(@PathVariable Long id)
	{
		return ResponseEntity.ok(languageService.delete(id));
	}

}
