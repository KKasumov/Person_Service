package com.kasumov.Person_Service.controller;

import com.kasumov.Person_Service.service.VerificationStatusService;
import com.kasumov.DTO.VerificationStatusDTO;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/verification_statuses/")
public class VerificationStatusController {

    private final VerificationStatusService verificationStatusService;

    public VerificationStatusController(VerificationStatusService verificationStatusService) {
        this.verificationStatusService = verificationStatusService;
    }


    @PostMapping("/new_status")
    public Mono<VerificationStatusDTO> createStatus(@RequestBody VerificationStatusDTO verificationStatusDTO) {
        return verificationStatusService.save(verificationStatusDTO);
    }

    @PostMapping("/update")
    public Mono<VerificationStatusDTO> updateStatus(@RequestBody VerificationStatusDTO verificationStatusDTO) {
        return verificationStatusService.update(verificationStatusDTO);
    }

    @GetMapping("/{id}")
    public Mono<VerificationStatusDTO> getStatus(@PathVariable String id) {
        return verificationStatusService.getById(UUID.fromString(id));
    }

    @GetMapping("/all")
    public Flux<VerificationStatusDTO> getAll() {
        return verificationStatusService.getAll();
    }


}
