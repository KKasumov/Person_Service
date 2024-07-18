/*
package usersmanager.rest;

import com.kasumov.DTO.IndividualDTO;
import com.kasumov.Person_Service.model.Individual;
import com.kasumov.Person_Service.repository.IndividualRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient
public class IndividualControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private IndividualRepository individualRepository;

    @BeforeEach
    public void setup() {
        individualRepository.deleteAll().block();
    }

    @Test
    public void testCreateNewUser() {
        IndividualDTO individualDTO = IndividualDTO.builder()
                .passport_number("A1234567")
                .phone_number("123-456-7890")
                .email("test@example.com")
                .status("active")
                .user_id(1L)
                .build();

        webTestClient.post()
                .uri("/api/v1/individuals/new_user")
                .body(Mono.just(individualDTO), IndividualDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(IndividualDTO.class)
                .value(response -> {
                    assertThat(response.getId()).isNotNull();
                    assertThat(response.getPassport_number()).isEqualTo("A1234567");
                    assertThat(response.getPhone_number()).isEqualTo("123-456-7890");
                    assertThat(response.getEmail()).isEqualTo("test@example.com");
                    assertThat(response.getStatus()).isEqualTo("active");
                });
    }

    @Test
    public void testGetAll() {
        Individual individual1 = new Individual();
        individual1.setId(UUID.randomUUID());
        individual1.setCreatedAt(LocalDateTime.now());
        individual1.setUpdatedAt(LocalDateTime.now());
        individual1.setPassport_number("A1234567");
        individual1.setPhone_number("123-456-7890");
        individual1.setEmail("test1@example.com");
        individual1.setStatus("active");
        individual1.setUser_id(new UUID(0, 1L));

        Individual individual2 = new Individual();
        individual2.setId(UUID.randomUUID());
        individual2.setCreatedAt(LocalDateTime.now());
        individual2.setUpdatedAt(LocalDateTime.now());
        individual2.setPassport_number("B7654321");
        individual2.setPhone_number("098-765-4321");
        individual2.setEmail("test2@example.com");
        individual2.setStatus("inactive");
        individual2.setUser_id(new UUID(0, 2L));

        individualRepository.save(individual1).block();
        individualRepository.save(individual2).block();

        webTestClient.get()
                .uri("/api/v1/individuals/all")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(IndividualDTO.class)
                .value(response -> assertThat(response).hasSize(2));
    }

    @Test
    public void testGetById() {
        UUID id = UUID.randomUUID();
        Individual individual = new Individual();
        individual.setId(id);
        individual.setCreatedAt(LocalDateTime.now());
        individual.setUpdatedAt(LocalDateTime.now());
        individual.setPassport_number("A1234567");
        individual.setPhone_number("123-456-7890");
        individual.setEmail("test@example.com");
        individual.setStatus("active");
        individual.setUser_id(new UUID(0, 1L));

        individualRepository.save(individual).block();

        webTestClient.get()
                .uri("/api/v1/individuals/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(IndividualDTO.class)
                .value(response -> assertThat(response.getId()).isEqualTo(id));
    }

    @Test
    public void testDeleteUser() {
        UUID id = UUID.randomUUID();
        Individual individual = new Individual();
        individual.setId(id);
        individual.setCreatedAt(LocalDateTime.now());
        individual.setUpdatedAt(LocalDateTime.now());
        individual.setPassport_number("A1234567");
        individual.setPhone_number("123-456-7890");
        individual.setEmail("test@example.com");
        individual.setStatus("active");
        individual.setUser_id(new UUID(0, 1L));

        individualRepository.save(individual).block();

        webTestClient.delete()
                .uri("/api/v1/individuals/" + id)
                .exchange()
                .expectStatus().isOk();

        webTestClient.get()
                .uri("/api/v1/individuals/" + id)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void testUpdateUser() {
        UUID id = UUID.randomUUID();
        Individual individual = new Individual();
        individual.setId(id);
        individual.setCreatedAt(LocalDateTime.now());
        individual.setUpdatedAt(LocalDateTime.now());
        individual.setPassport_number("A1234567");
        individual.setPhone_number("123-456-7890");
        individual.setEmail("test@example.com");
        individual.setStatus("active");
        individual.setUser_id(new UUID(0, 1L));
        individualRepository.save(individual).block();

        IndividualDTO updatedDTO = IndividualDTO.builder()
                .passport_number("B7654321")
                .phone_number("098-765-4321")
                .email("updated@example.com")
                .status("inactive")
                .user_id(2L)
                .build();

        webTestClient.put()
                .uri("/api/v1/individuals/" + id)
                .body(Mono.just(updatedDTO), IndividualDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(IndividualDTO.class)
                .value(response -> {
                    assertThat(response.getPassport_number()).isEqualTo("B7654321");
                    assertThat(response.getPhone_number()).isEqualTo("098-765-4321");
                    assertThat(response.getEmail()).isEqualTo("updated@example.com");
                    assertThat(response.getStatus()).isEqualTo("inactive");
                });
    }
}*/
