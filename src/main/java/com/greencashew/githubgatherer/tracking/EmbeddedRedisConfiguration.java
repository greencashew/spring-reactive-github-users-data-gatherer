package com.greencashew.githubgatherer.tracking;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@Slf4j
@Configuration
class EmbeddedRedisConfiguration {
    private final RedisServer redisServer;

    EmbeddedRedisConfiguration(@Value("${spring.redis.port}") final int port) throws IOException {
        this.redisServer = new RedisServer(port);
    }

    @PostConstruct
    public void startRedis() {
        redisServer.start();
        log.info("Redis embedded server started.");
    }

    @PreDestroy
    public void stopRedis() {
        redisServer.stop();
        log.info("Redis embedded server stopped.");
    }
}
