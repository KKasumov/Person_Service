package com.kasumov.Person_Service.service;

import com.kasumov.DTO.IndividualRegistrationDto;
import com.kasumov.DTO.UserDTO;
import com.kasumov.DTO.UserHistoryDTO;

import com.kasumov.DTO.request.UpdateRequestIndividualDTO;
import com.kasumov.DTO.responce.IndividualDTOResponse;
import com.kasumov.Person_Service.mapper.UserHistoryMapper;
import com.kasumov.Person_Service.mapper.UserMapper;
import com.kasumov.Person_Service.repository.IndividualRepository;
import com.kasumov.Person_Service.mapper.IndividualMapper;
import com.kasumov.DTO.IndividualDTO;
import com.kasumov.Person_Service.repository.UserHistoryRepository;
import com.kasumov.Person_Service.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class IndividualService {


    private final IndividualRepository individualRepository;
    private final IndividualMapper individualMapper;
    private final UserHistoryRepository userHistoryRepository;
    private final UserHistoryMapper userHistoryMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public IndividualService(IndividualRepository individualRepository,
                             IndividualMapper individualMapper,
                             UserHistoryRepository userHistoryRepository,
                             UserHistoryMapper userHistoryMapper,
                             UserRepository userRepository,
                             UserMapper userMapper)
    {
        this.individualRepository = individualRepository;
        this.individualMapper = individualMapper;
        this.userHistoryRepository = userHistoryRepository;
        this.userHistoryMapper = userHistoryMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;

    }


    public Mono<IndividualDTO> save(IndividualDTO individualDTO) {
        individualDTO.setUpdatedAt(LocalDateTime.now());
        individualDTO.setCreatedAt(LocalDateTime.now());
        return individualRepository.save(individualMapper.convert(individualDTO))
                .map(individualMapper::convert);
    }

    public Flux<IndividualDTO> getAll() {
        return individualRepository.findAll()
                .map(individualMapper::convert);
    }

    public Mono<IndividualDTO> getById(UUID id) {
        return individualRepository.findById(id)
                .map(individualMapper::convert);
    }

    public Mono<IndividualDTOResponse> create(IndividualRegistrationDto updateRequest) {
        LocalDateTime now = LocalDateTime.now();

        // Создаем UserDTO
        UserDTO userDTO = updateRequest.getUserDTO();
        userDTO.setCreatedAt(now);
        userDTO.setUpdatedAt(now);
        userDTO.setStatus("ACTIVE");
        userDTO.setFilled(false);

        // Сохраняем UserDTO
        return userRepository.save(userMapper.convert(userDTO))
                .flatMap(savedUser -> {
                    // Создаем IndividualDTO
                    IndividualDTO individualDTO = updateRequest.getIndividualDTO();
                    individualDTO.setCreatedAt(now);
                    individualDTO.setUpdatedAt(now);
                    individualDTO.setStatus("NEW");
                    individualDTO.setUser_id(savedUser.getId());

                    // Сохраняем IndividualDTO
                    return individualRepository.save(individualMapper.convert(individualDTO))
                            .flatMap(savedIndividual -> {
                                // Создаем UserHistoryDTO
                                UserHistoryDTO userHistoryDTO = new UserHistoryDTO();
                                userHistoryDTO.setCreatedAt(now);
                                userHistoryDTO.setUpdatedAt(now);
                                userHistoryDTO.setProfile_type("INDIVIDUAL");
                                userHistoryDTO.setReason("REGISTRATION");
                                userHistoryDTO.setProfile_id(savedIndividual.getId());

                                return userHistoryRepository.save(userHistoryMapper.convert(userHistoryDTO))
                                        .map(savedHistory -> {
                                            // Возвращаем IndividualDTOResponse
                                            return IndividualDTOResponse.builder()
                                                    .userDTO(userMapper.convert(savedUser))
                                                    .individualDTO(individualMapper.convert(savedIndividual))
                                                    .build();
                                        });
                            });
                });
    }

    public Mono<IndividualDTO> update(UUID id, UpdateRequestIndividualDTO updateRequest) {
        return individualRepository.findById(id)
                .flatMap(existingIndividual -> {
                    // Копирование ID и даты создания
                    updateRequest.getIndividualDTO().setId(existingIndividual.getId());
                    updateRequest.getIndividualDTO().setCreatedAt(existingIndividual.getCreatedAt());
                    updateRequest.getIndividualDTO().setUpdatedAt(LocalDateTime.now());

                    // Получаем ID пользователя
                    UUID userId = existingIndividual.getId();

                    // Проверяем, нужно ли обновлять данные пользователя
                    if (updateRequest.getUserDTO() != null) {
                        assert userId != null;
                        return userRepository.findById(userId)
                                .flatMap(user -> {
                                    // Обновляем данные пользователя
                                    user.setFirst_name(updateRequest.getUserDTO().getFirst_name() != null ? updateRequest.getUserDTO().getFirst_name() : user.getFirst_name());
                                    user.setLast_name(updateRequest.getUserDTO().getLast_name() != null ? updateRequest.getUserDTO().getLast_name() : user.getLast_name());
                                    user.setEmail(updateRequest.getUserDTO().getEmail() != null ? updateRequest.getUserDTO().getEmail() : user.getEmail());
                                    user.setUpdatedAt(LocalDateTime.now());
                                    return userRepository.save(user);
                                })
                                .thenReturn(existingIndividual); // Возвращаем существующего индивидуума
                    } else {
                        return Mono.just(existingIndividual); // Возвращаем существующего индивидуума без изменений
                    }
                })
                .flatMap(updatedIndividual -> {
                    // Сохраняем изменения в индивидууме
                    return individualRepository.save(individualMapper.convert(updateRequest.getIndividualDTO()))
                            .flatMap(savedIndividual -> {
                                // Обновляем историю пользователя
                                UserHistoryDTO userHistoryDTO = new UserHistoryDTO();
                                userHistoryDTO.setCreatedAt(LocalDateTime.now());
                                userHistoryDTO.setUpdatedAt(LocalDateTime.now());
                                userHistoryDTO.setProfile_type("INDIVIDUAL");
                                userHistoryDTO.setReason("UPDATE");
                                userHistoryDTO.setProfile_id(savedIndividual.getId());

                                return userHistoryRepository.save(userHistoryMapper.convert(userHistoryDTO))
                                        .thenReturn(individualMapper.convert(savedIndividual)); // Возвращаем обновленный IndividualDTO
                            });
                });
    }
    public Mono<IndividualDTO> deleteById(UUID id) {
        return individualRepository.findById(id)
                .flatMap(individual -> {
                    // Получаем ID пользователя
                    UUID userId = individual.getUser_id();

                    // Удаляем информацию об индивидууме
                    return individualRepository.deleteById(id)
                            .then(userRepository.findById(userId))
                            .flatMap(user -> {
                                // Создаем запись в истории об удалении
                                UserHistoryDTO userHistoryDTO = new UserHistoryDTO();
                                userHistoryDTO.setCreatedAt(LocalDateTime.now());
                                userHistoryDTO.setUpdatedAt(LocalDateTime.now());
                                userHistoryDTO.setProfile_type("INDIVIDUAL");
                                userHistoryDTO.setReason("DELETE");
                                userHistoryDTO.setProfile_id(id);

                                return userHistoryRepository.save(userHistoryMapper.convert(userHistoryDTO));
                            })
                            .thenReturn(individualMapper.convert(individual));
                })
                .switchIfEmpty(Mono.empty()); // Если индивидуум не найден, возвращаем пустой Mono
    }


}







