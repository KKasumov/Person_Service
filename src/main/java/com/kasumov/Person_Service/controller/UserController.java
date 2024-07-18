package com.kasumov.Person_Service.controller;

import com.kasumov.Person_Service.service.UserService;
import com.kasumov.DTO.UserDTO;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/users/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("new_user")
    public Mono<UserDTO> createNewUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @GetMapping("all")
    public Flux<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public Mono<UserDTO> getById(@PathVariable("id") String id) {
        return userService.getById(UUID.fromString(id));
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteById(@PathVariable("id") String id) {
        return userService.deleteById(UUID.fromString(id));
    }

    @PutMapping("{id}")
    public Mono<UserDTO> updateUser(@PathVariable("id") String id,
                                    @RequestBody UserDTO userDTO) {
        return userService.update(UUID.fromString(id), userDTO);
    }
}