package com.greencashew.githubgatherer.gather.user;

import com.greencashew.githubgatherer.dto.UserDataDto;
import reactor.core.publisher.Flux;

public interface GithubUserDataGatherer {
    Flux<UserDataDto> getDataFor(Flux<String> logins);

}
