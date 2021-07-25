package com.greencashew.githubgatherer.api;

import java.util.Set;

import lombok.Value;

@Value
class UserDataRequest {
    private static final String LOGIN_SEPARATOR = ",";
    private static final String WHITESPACE_REGEX = "\\s+";

    String logins;

    Set<String> getLogins() {
        return Set.of(logins.replaceAll(WHITESPACE_REGEX, "").split(LOGIN_SEPARATOR));
    }
}
