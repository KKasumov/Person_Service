package com.kasumov.Person_Service.controller;

import com.kasumov.DTO.IndividualRegistrationDto;
import com.kasumov.DTO.request.UpdateRequestIndividualDTO;
import com.kasumov.DTO.responce.IndividualDTOResponse;
import com.kasumov.Person_Service.service.IndividualService;
import com.kasumov.DTO.IndividualDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/individuals/")
public class IndividualController {

    private final IndividualService individualService;

    public IndividualController(IndividualService individualService) {
        this.individualService = individualService;
    }

    @PostMapping("/new_individual")
    public Mono<ResponseEntity<IndividualDTOResponse>> createNewIndividual(@RequestBody IndividualRegistrationDto registrationDto) {
        return individualService.create(registrationDto)
                .map(individualDTOResponse -> ResponseEntity.status(HttpStatus.CREATED).body(individualDTOResponse));
    }

    @GetMapping("/all")
    public Flux<IndividualDTO> getAll() {
        return individualService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<IndividualDTO> getById(@PathVariable("id") String id) {
        return individualService.getById(UUID.fromString(id));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") String id) {
        return individualService.deleteById(UUID.fromString(id)).then();
    }

    @PutMapping("/{id}")
    public Mono<IndividualDTO> updateUser(@PathVariable("id") String id,
                                          @RequestBody UpdateRequestIndividualDTO individualDTO) {
        return individualService.update(UUID.fromString(id), individualDTO);
    }

    @PostMapping
    public Mono<IndividualDTO> save(@RequestBody IndividualDTO individualDTO) {
        return individualService.save(individualDTO);
    }

}