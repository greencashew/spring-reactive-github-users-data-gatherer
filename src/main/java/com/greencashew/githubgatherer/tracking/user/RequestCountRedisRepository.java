package com.greencashew.githubgatherer.tracking.user;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class RequestCountRedisRepository implements RequestCountRepository {
    private final ReactiveRedisOperations<String, Integer> operations;

    @Override
    public Mono<LoginRequest> save(final LoginRequest request) {
        return operations.opsForValue()
                .set(request.key(), request.count())
                .map(__ -> request);
    }

    @Override
    public Mono<LoginRequest> findRequestByKey(final String key) {
        return operations.opsForValue()
                .get(key)
                .map(count -> new LoginRequest(key, count));
    }

    @Override
    public Mono<Long> increment(final String key) {
        return operations.opsForValue()
                .increment(key);
    }
}
