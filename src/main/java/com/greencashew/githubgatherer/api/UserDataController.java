package com.greencashew.githubgatherer.api;

import com.greencashew.githubgatherer.gather.user.GithubUserDataGatherer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
class UserDataController {

    private final GithubUserDataGatherer dataGatherer;

    @GetMapping(path = "/users/{logins}")
    public Flux<UserDataResponse> getDataForLogins(@PathVariable UserDataRequest logins) {

        return dataGatherer.getDataFor(logins.getLogins())
                .flatMap(userDataDto -> Mono.just(UserDataResponse.fromDto(userDataDto)));
    }
}
