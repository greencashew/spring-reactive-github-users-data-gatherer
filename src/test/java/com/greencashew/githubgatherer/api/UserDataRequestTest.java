package com.greencashew.githubgatherer.api;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UserDataRequestTest {

    @Test
    void shouldGetLoginsWithoutRedundantWhitespaces() throws Exception {

        final UserDataRequest userDataRequest = new UserDataRequest("greencashew,janek, login, white    ,space ");

        assertThat(Set.of("greencashew", "janek", "login", "white", "space")).hasSameElementsAs(userDataRequest.getLogins());
    }
}