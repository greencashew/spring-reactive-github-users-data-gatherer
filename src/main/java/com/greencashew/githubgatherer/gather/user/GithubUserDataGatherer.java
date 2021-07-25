package com.greencashew.githubgatherer.gather.user;

import java.util.Set;

import com.greencashew.githubgatherer.dto.UserDataDto;
import reactor.core.publisher.Flux;

public interface GithubUserDataGatherer {
    Flux<UserDataDto> getDataFor(Set<String> logins);

}
