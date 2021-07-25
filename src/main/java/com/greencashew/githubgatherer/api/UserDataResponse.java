package com.greencashew.githubgatherer.api;

import com.greencashew.githubgatherer.dto.UserDataDto;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
class UserDataResponse {
    long id;
    String login;
    String name;
    String type;
    String avatarUrl;
    String createdAt;
    double calculations;

    static UserDataResponse fromDto(UserDataDto userDataDto) {
        return UserDataResponse.builder()
                .id(userDataDto.getId())
                .login(userDataDto.getLogin())
                .name(userDataDto.getName())
                .type(userDataDto.getType())
                .avatarUrl(userDataDto.getAvatarUrl())
                .createdAt(userDataDto.getCreatedAt())
                .calculations(userDataDto.getCalculations())
                .build();
    }
}
