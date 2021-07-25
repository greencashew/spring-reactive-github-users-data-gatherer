package com.greencashew.githubgatherer.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

import com.greencashew.githubgatherer.dto.UserDataDto;
import com.greencashew.githubgatherer.gather.user.GithubUserDataGatherer;
import com.greencashew.githubgatherer.tracking.user.UserRequestCounter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@ExtendWith({SpringExtension.class})
@WebFluxTest(controllers = UserDataController.class)
class UserDataControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GithubUserDataGatherer dataGatherer;
    @MockBean
    private UserRequestCounter userRequestCounter;

    @Test
    void shouldProperlyGatherExampleUserDetails() {
        final UserDataDto exampleUserData = UserDataDto.builder()
                .id(16562156)
                .login("greencashew")
                .name("Jan Górkiewicz")
                .type("User")
                .avatarUrl("https://avatars.githubusercontent.com/u/16562156?v=4")
                .createdAt("2016-01-05T16:40:24Z")
                .calculations(4.52)
                .build();

        when(dataGatherer.getDataFor(any())).thenReturn(Flux.just(exampleUserData));

        webTestClient.get()
                .uri("/users/" + exampleUserData.getLogin())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .consumeWith(entityExchangeResult -> System.out.println("Returned body: " + entityExchangeResult))
                .jsonPath("$[0].id")
                .value((Integer id) -> assertEquals((int) exampleUserData.getId(), id))
                .jsonPath("$[0].login")
                .value(login -> assertEquals(exampleUserData.getLogin(), login))
                .jsonPath("$[0].name")
                .value(name -> assertEquals(exampleUserData.getName(), name))
                .jsonPath("$[0].type")
                .value(user -> assertEquals(exampleUserData.getType(), user))
                .jsonPath("$[0].avatarUrl")
                .value(avatarUrl -> assertEquals(exampleUserData.getAvatarUrl(), avatarUrl))
                .jsonPath("$[0].createdAt")
                .value(createdAt -> assertEquals(exampleUserData.getCreatedAt(), createdAt))
                .jsonPath("$[0].calculations")
                .value(calculations -> assertEquals(exampleUserData.getCalculations(), calculations));

        then(userRequestCounter).should().notifyUsersRequested(any());
    }
}