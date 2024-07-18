package com.kasumov.Person_Service.controller;

import com.kasumov.DTO.MerchantDTO;
import com.kasumov.Person_Service.service.MerchantService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequestMapping("api/v1/merchants/")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }


    @PostMapping("new_user")
    public Mono<MerchantDTO> createNewUser(@RequestBody MerchantDTO merchantDTO) {
        return merchantService.save(merchantDTO);
    }

    @GetMapping("/all")
    public Flux<MerchantDTO> getAll() {
        return merchantService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<MerchantDTO> getById(@PathVariable("id") String id) {
        return merchantService.getById(UUID.fromString(id));
    }
}