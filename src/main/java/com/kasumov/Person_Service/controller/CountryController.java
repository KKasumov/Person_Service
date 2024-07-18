package com.kasumov.Person_Service.controller;

import com.kasumov.Person_Service.service.CountryService;
import com.kasumov.DTO.CountryDTO;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequestMapping("api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @PostMapping("/new_user")
    public Mono<CountryDTO> createNewUser(@RequestBody CountryDTO countryDTO) {
        return countryService.save(countryDTO);
    }

    @GetMapping("/all")
    public Flux<CountryDTO> getAll() {
        return countryService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<CountryDTO> getById(@PathVariable("id") UUID id) {
        return countryService.getById(id);
    }
}
