package com.greencashew.githubgatherer.dto;

import com.greencashew.githubgatherer.api.LoginRequestsCountResponse;

public record LoginRequestCountDto(String key, Integer count) implements LoginRequestsCountResponse {
}
