package com.greencashew.githubgatherer.gather.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class GithubUserDataGathererConfiguration {

    @Bean
    public GithubUserDataGatherer getWebClient(@Value("${github.api.url}") final String githubApiUrl) {
        return new GithubUserDataGathererImpl(WebClient.create(githubApiUrl));
    }
}
