package com.greencashew.githubgatherer.api;

import com.greencashew.githubgatherer.gather.user.GithubUserDataGatherer;
import com.greencashew.githubgatherer.tracking.user.UserRequestCounter;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "Gather users data from github api",
            notes = "Users are sorted by calculations score",
            response = UserDataResponse.class)
    @GetMapping(path = "/users/{logins}")
    public Flux<UserDataResponse> getDataForLogins(
            @ApiParam(value = "Comma separated list of github logins or single login.", example = "greencashew", required = true)
            @PathVariable UserDataRequest logins) {
        final Flux<String> loginStream = Flux.fromIterable(logins.getLogins());
        userRequestCounter.notifyUsersRequested(loginStream);

        return dataGatherer.getDataFor(loginStream)
                .flatMap(userDataDto -> Mono.just(UserDataResponse.fromDto(userDataDto)));
    }

    @ApiOperation(value = "Get information how many times user data were requested",
            notes = "Missing responded information means that the concrete login were not found in db.",
            response = LoginRequestsCountResponse.class)
    @GetMapping(path = "/users/requests/{logins}")
    public Flux<LoginRequestsCountResponse> getRequests(
            @ApiParam(value = "Comma separated list of github logins or single login.", example = "greencashew", required = true)
            @PathVariable UserDataRequest logins
    ) {
        return userRequestCounter.getUsersRequests(Flux.fromIterable(logins.getLogins()))
                .flatMap(loginRequestCountDto -> Mono.just(LoginRequestsCountResponse.fromDto(loginRequestCountDto)));
    }
}
