package com.greencashew.githubgatherer.tracking.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class RequestCountRedisRepositoryTest {

    @Autowired
    private RequestCountRedisRepository repository;

    @Test
    void shouldSaveRequestInRedis() {
        final String exampleUserKey = "greencashew";
        final LoginRequest exampleRequest = new LoginRequest(exampleUserKey, 0);

        StepVerifier.create(
                repository.save(exampleRequest)
                        .flatMap(__ -> repository.findRequestByKey(exampleUserKey))
        )
                .expectNext(exampleRequest)
                .verifyComplete();

    }
}