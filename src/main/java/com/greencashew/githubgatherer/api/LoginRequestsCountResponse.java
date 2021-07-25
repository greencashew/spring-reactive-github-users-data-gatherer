package com.greencashew.githubgatherer.api;


import com.greencashew.githubgatherer.dto.LoginRequestCountDto;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
class LoginRequestsCountResponse {
    String key;
    Integer count;

    static LoginRequestsCountResponse fromDto(final LoginRequestCountDto value) {
        return new LoginRequestsCountResponse(value.key(), value.count());
    }
}
