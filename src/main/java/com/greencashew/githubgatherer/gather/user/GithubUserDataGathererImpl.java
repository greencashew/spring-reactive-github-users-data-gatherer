package com.greencashew.githubgatherer.gather.user;

import com.greencashew.githubgatherer.dto.UserDataDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@AllArgsConstructor
class GithubUserDataGathererImpl implements GithubUserDataGatherer {
    private final WebClient webClient;

    @Override
    public Flux<UserDataDto> getDataFor(final Flux<String> loginStream) {
        return loginStream
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .flatMap(this::getUserData)
                .flatMap(this::convertToUserDataDto)
                .ordered((u1, u2) -> (int) (u2.getCalculations() - u1.getCalculations()));
    }

    public Mono<UserData> getUserData(final String login) {
        return webClient.get()
                .uri("/users/{login}", login)
                .retrieve()
                .bodyToMono(UserData.class)
                .doOnError(throwable -> log.error(throwable.getMessage()));

    }

    private Publisher<UserDataDto> convertToUserDataDto(final UserData userData) {
        return Mono.just(
                UserDataDto.builder()
                        .id(userData.id())
                        .login(userData.login())
                        .name(userData.name())
                        .avatarUrl(userData.avatar_url())
                        .createdAt(userData.created_at())
                        .type(userData.type())
                        .calculations(userData.getScore())
                        .build()
        );
    }

}
