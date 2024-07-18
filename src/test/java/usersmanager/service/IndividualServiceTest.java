package usersmanager.service;

import com.kasumov.DTO.IndividualDTO;
import com.kasumov.DTO.request.UpdateRequestIndividualDTO;
import com.kasumov.Person_Service.model.Individual;
import com.kasumov.Person_Service.repository.IndividualRepository;
import com.kasumov.Person_Service.mapper.IndividualMapper;
import com.kasumov.Person_Service.service.IndividualService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class IndividualServiceTest {

    @Mock
    private IndividualRepository individualRepository;

    @Mock
    private IndividualMapper individualMapper;

    @InjectMocks
    private IndividualService individualService;

    private IndividualDTO individualDTO;
    private Individual individual;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        UUID id = UUID.randomUUID();
        individualDTO = IndividualDTO.builder()
                .id(id)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .passport_number("123456789")
                .phone_number("1234567890")
                .email("test@example.com")
                .status("ACTIVE")
                .user_id(UUID.randomUUID())
                .build();

        individual = Individual.builder()
                .id(id)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .passport_number("123456789")
                .phone_number("1234567890")
                .email("test@example.com")
                .status("ACTIVE")
                .user_id(UUID.randomUUID())
                .build();
    }

    @Test
    public void saveIndividualTest() {
        when(individualMapper.convert(individualDTO)).thenReturn(individual);
        when(individualRepository.save(individual)).thenReturn(Mono.just(individual));
        when(individualMapper.convert(individual)).thenReturn(individualDTO);

        Mono<IndividualDTO> savedIndividual = individualService.save(individualDTO);

        StepVerifier.create(savedIndividual)
                .expectNextMatches(individual -> individual.getId().equals(individualDTO.getId()))
                .verifyComplete();
    }

    @Test
    public void getAllIndividualsTest() {
        when(individualRepository.findAll()).thenReturn(Flux.just(individual));
        when(individualMapper.convert(individual)).thenReturn(individualDTO);

        Flux<IndividualDTO> allIndividuals = individualService.getAll();

        StepVerifier.create(allIndividuals)
                .expectNextMatches(individual -> individual.getEmail().equals(individualDTO.getEmail()))
                .verifyComplete();
    }

    @Test
    public void getIndividualByIdTest() {
        UUID id = individualDTO.getId();
        when(individualRepository.findById(id)).thenReturn(Mono.just(individual));
        when(individualMapper.convert(individual)).thenReturn(individualDTO);

        Mono<IndividualDTO> individualById = individualService.getById(id);

        StepVerifier.create(individualById)
                .expectNextMatches(foundIndividual -> foundIndividual.getId().equals(id))
                .verifyComplete();
    }

    @Test
    public void deleteIndividualByIdTest() {
        UUID id = individualDTO.getId();
        when(individualRepository.deleteById(id)).thenReturn(Mono.empty());

        Mono<Void> deletedIndividual = individualService.deleteById(id).then();

        StepVerifier.create(deletedIndividual)
                .verifyComplete();
    }

    @Test
    public void updateIndividualTest() {
        UUID id = individualDTO.getId();

        // Создайте объект UpdateRequestIndividualDTO
        UpdateRequestIndividualDTO updateRequest = new UpdateRequestIndividualDTO();
        updateRequest.setIndividualDTO(individualDTO);

        // Мокинг
        when(individualRepository.findById(id)).thenReturn(Mono.just(individual));
        when(individualMapper.convert(updateRequest.getIndividualDTO())).thenReturn(individual);
        when(individualRepository.save(individual)).thenReturn(Mono.just(individual));
        when(individualMapper.convert(individual)).thenReturn(individualDTO);

        // Вызов метода update с правильным аргументом
        Mono<IndividualDTO> updatedIndividual = individualService.update(id, updateRequest);

        StepVerifier.create(updatedIndividual)
                .expectNextMatches(updated -> updated.getEmail().equals(individualDTO.getEmail()) && updated.getStatus().equals(individualDTO.getStatus()))
                .verifyComplete();
    }
}