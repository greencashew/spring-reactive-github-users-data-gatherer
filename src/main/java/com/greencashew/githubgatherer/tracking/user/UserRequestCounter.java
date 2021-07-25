package com.greencashew.githubgatherer.tracking.user;

import com.greencashew.githubgatherer.dto.LoginRequestCountDto;
import reactor.core.publisher.Flux;

public interface UserRequestCounter {

    void notifyUsersRequested(Flux<String> loginStream);

    Flux<LoginRequestCountDto> getUsersRequests(Flux<String> loginStream);
}
