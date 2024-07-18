package com.kasumov.Person_Service.controller;

import com.kasumov.Person_Service.service.UserHistoryService;
import com.kasumov.DTO.UserHistoryDTO;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/profile_history/")
public class UserHistoryController {

    private final UserHistoryService userHistoryService;

    public UserHistoryController(UserHistoryService userHistoryService) {
        this.userHistoryService = userHistoryService;
    }

    @PostMapping("new_user")
    public Mono<UserHistoryDTO> createNewUser(
            @RequestBody UserHistoryDTO userHistoryDTO) {
        return userHistoryService.save(userHistoryDTO);
    }

    @GetMapping("/all")
    public Flux<UserHistoryDTO> getAll() {
        return userHistoryService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<UserHistoryDTO> getById(@PathVariable("id") String id) {
        return userHistoryService.getById(UUID.fromString(id));
    }

    @PutMapping("/{id}")
    public Mono<UserHistoryDTO> updateUserHistory(@PathVariable("id") String id,
                                                  @RequestBody UserHistoryDTO userHistoryDTO) {
        return userHistoryService.update(UUID.fromString(id), userHistoryDTO);
    }
}