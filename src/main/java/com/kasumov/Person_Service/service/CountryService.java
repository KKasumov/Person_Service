package com.kasumov.Person_Service.service;

import com.kasumov.Person_Service.repository.CountryRepository;
import com.kasumov.Person_Service.mapper.CountryMapper;
import com.kasumov.DTO.CountryDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }


    public Mono<CountryDTO> save(CountryDTO countryDTO){
        countryDTO.setUpdatedAt(LocalDateTime.now());
        countryDTO.setCreatedAt(LocalDateTime.now());
        return countryRepository.save(countryMapper.convert(countryDTO))
                .map(countryMapper::convert);
    }

    public Flux<CountryDTO> getAll() {
        return countryRepository.findAll()
                .map(countryMapper::convert);

    }

    public Mono<CountryDTO> getById(UUID id){
        return countryRepository.findById(id)
                .map(countryMapper::convert);
    }
}
