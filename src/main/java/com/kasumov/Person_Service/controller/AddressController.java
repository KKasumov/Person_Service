package com.kasumov.Person_Service.controller;

import com.kasumov.DTO.AddressDTO;
import com.kasumov.Person_Service.service.AddressService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/new_address")
    public Mono<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO){
        return addressService.saveAddress(addressDTO);
    }

    @GetMapping("/all")
    public Flux<AddressDTO> getAll(){
        return addressService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<AddressDTO> getById(@PathVariable("id") String id){
        return addressService.getById(UUID.fromString(id));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAddress(@PathVariable("id") String id) {
        return addressService.deleteAddress(UUID.fromString(id));
    }
}