package com.greencashew.githubgatherer.tracking.user;

import reactor.core.publisher.Mono;

interface RequestCountRepository {
    Mono<LoginRequest> save(LoginRequest request);

    Mono<LoginRequest> findRequestByKey(String key);

    Mono<Long> increment(String key);
}
