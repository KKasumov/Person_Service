package com.kasumov.Person_Service.service;

import com.kasumov.Person_Service.repository.UserRepository;
import com.kasumov.Person_Service.mapper.UserMapper;
import com.kasumov.DTO.UserDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public record UserService(UserRepository userRepository,
                          UserMapper userMapper) {

    public Mono<UserDTO> save(UserDTO userDTO){
        userDTO.setUpdatedAt(LocalDateTime.now());
        userDTO.setCreatedAt(LocalDateTime.now());
        return userRepository.save(userMapper.convert(userDTO))
                .map(userMapper::convert);
    }

    public Flux<UserDTO> getAll() {
        return userRepository.findAll()
                .map(userMapper::convert);
    }

    public Mono<UserDTO> getById(UUID id){
        return userRepository.findById(id)
                .map(userMapper::convert);
    }

    public Mono<Void> deleteById(UUID id) {
        return userRepository.deleteById(id);
    }

    public Mono<UserDTO> update(UUID id, UserDTO userDTO) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    userDTO.setId(existingUser.getId());
                    userDTO.setCreatedAt(existingUser.getCreatedAt());
                    userDTO.setUpdatedAt(LocalDateTime.now());
                    return userRepository.save(userMapper.convert(userDTO));
                })
                .map(userMapper::convert);
    }
}