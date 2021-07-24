package com.greencashew.githubgatherer.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
class UserController {
    private static final String LOGIN_SEPARATOR = ",";

    @GetMapping(path = "/users/{logins}")
    public Flux<String> get(@PathVariable String logins) {
        final String[] loginList = logins.split(LOGIN_SEPARATOR);
        return Flux.just(loginList);
    }
}
