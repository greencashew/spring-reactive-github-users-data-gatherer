package com.greencashew.githubgatherer.gather.user;

import com.greencashew.githubgatherer.dto.UserDataDto;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class GithubUserDataGathererImplTest extends MockWebServerAbstractTest {
    private static final String EXAMPLE_USER_API_RESPONSE = """
            {
              "login": "greencashew",
              "id": 16562156,
              "node_id": "MDQ6VXNlcjE2NTYyMTU2",
              "avatar_url": "https://avatars.githubusercontent.com/u/16562156?v=4",
              "gravatar_id": "",
              "url": "https://api.github.com/users/greencashew",
              "html_url": "https://github.com/greencashew",
              "followers_url": "https://api.github.com/users/greencashew/followers",
              "following_url": "https://api.github.com/users/greencashew/following{/other_user}",
              "gists_url": "https://api.github.com/users/greencashew/gists{/gist_id}",
              "starred_url": "https://api.github.com/users/greencashew/starred{/owner}{/repo}",
              "subscriptions_url": "https://api.github.com/users/greencashew/subscriptions",
              "organizations_url": "https://api.github.com/users/greencashew/orgs",
              "repos_url": "https://api.github.com/users/greencashew/repos",
              "events_url": "https://api.github.com/users/greencashew/events{/privacy}",
              "received_events_url": "https://api.github.com/users/greencashew/received_events",
              "type": "User",
              "site_admin": false,
              "name": "Jan Górkiewicz",
              "company": null,
              "blog": "https://greencashew.dev/",
              "location": null,
              "email": null,
              "hireable": null,
              "bio": null,
              "twitter_username": null,
              "public_repos": 8,
              "public_gists": 0,
              "followers": 4,
              "following": 4,
              "created_at": "2016-01-05T16:40:24Z",
              "updated_at": "2021-07-19T06:02:41Z"
            }
            {"mode":"full","isActive":false}
             """;
    private static final UserDataDto USER_DATA_DTO = UserDataDto.builder()
            .id(16562156)
            .login("greencashew")
            .name("Jan Górkiewicz")
            .type("User")
            .avatarUrl("https://avatars.githubusercontent.com/u/16562156?v=4")
            .createdAt("2016-01-05T16:40:24Z")
            .calculations(15)
            .build();


    private final GithubUserDataGatherer dataGatherer = new GithubUserDataGathererImpl(getWebClient());

    @Test
    void shouldGatherUserDataAndReturnDto() {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(EXAMPLE_USER_API_RESPONSE)
        );

        StepVerifier.create(dataGatherer.getDataFor(Flux.just("greencashew")))
                .expectNextMatches(userDataDto -> userDataDto.equals(USER_DATA_DTO))
                .verifyComplete();
    }
}