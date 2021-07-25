package com.greencashew.githubgatherer.api;

import com.greencashew.githubgatherer.dto.LoginRequestCountDto;
import com.greencashew.githubgatherer.gather.user.GithubUserDataGatherer;
import com.greencashew.githubgatherer.tracking.user.UserRequestCounter;
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
    private final UserRequestCounter userRequestCounter;

    @GetMapping(path = "/users/{logins}")
    public Flux<UserDataResponse> getDataForLogins(@PathVariable UserDataRequest logins) {
        final Flux<String> loginStream = Flux.fromIterable(logins.getLogins());
        userRequestCounter.notifyUsersRequested(loginStream);

        return dataGatherer.getDataFor(loginStream)
                .flatMap(userDataDto -> Mono.just(UserDataResponse.fromDto(userDataDto)));
    }

    @GetMapping(path = "/requests/{logins}")
    public Flux<LoginRequestCountDto> getRequests(@PathVariable UserDataRequest logins) {
        return userRequestCounter.getUsersRequests(Flux.fromIterable(logins.getLogins()));
    }
}
