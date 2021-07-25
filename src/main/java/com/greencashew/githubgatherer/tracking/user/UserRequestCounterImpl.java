package com.greencashew.githubgatherer.tracking.user;

import com.greencashew.githubgatherer.dto.LoginRequestCountDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
class UserRequestCounterImpl implements UserRequestCounter {

    private final RequestCountRepository repository;

    @Override
    public void notifyUsersRequested(final Flux<String> loginStream) {
        loginStream
                .flatMap(repository::increment)
                .doFinally(__ -> log.info("Login occurrences updated successfully!"))
                .subscribe();
    }

    @Override
    public Flux<LoginRequestCountDto> getUsersRequests(final Flux<String> loginStream) {
        return loginStream
                .flatMap(repository::findRequestByKey)
                .flatMap(loginRequest -> Mono.just(new LoginRequestCountDto(loginRequest.key(), loginRequest.count())));
    }
}
