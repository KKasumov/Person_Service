package com.kasumov.Person_Service.service;

import com.kasumov.Person_Service.mapper.UserHistoryMapper;
import com.kasumov.Person_Service.repository.UserHistoryRepository;
import com.kasumov.DTO.UserHistoryDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public record UserHistoryService(UserHistoryRepository userHistoryRepository,
                                 UserHistoryMapper userHistoryMapper) {

    public Mono<UserHistoryDTO> save(UserHistoryDTO userHistoryDTO){
        userHistoryDTO.setCreatedAt(LocalDateTime.now());
        return userHistoryRepository.save(userHistoryMapper.convert(userHistoryDTO))
                .map(userHistoryMapper::convert);
    }

    public Mono<UserHistoryDTO> getById(UUID id) {
        return userHistoryRepository.findById(id)
                .map(userHistoryMapper::convert);
    }

    public Flux<UserHistoryDTO> getAll() {
        return userHistoryRepository.findAll()
                .map(userHistoryMapper::convert);
    }

    public Mono<UserHistoryDTO> update(UUID id, UserHistoryDTO userHistoryDTO) {
        return userHistoryRepository.findById(id)
                .flatMap(existingUserHistory -> {
                    userHistoryDTO.setId(existingUserHistory.getId());
                    userHistoryDTO.setCreatedAt(existingUserHistory.getCreatedAt());
                    userHistoryDTO.setUpdatedAt(LocalDateTime.now());
                    return userHistoryRepository.save(userHistoryMapper.convert(userHistoryDTO));
                })
                .map(userHistoryMapper::convert);
    }
}