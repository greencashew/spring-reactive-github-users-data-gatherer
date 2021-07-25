package com.greencashew.githubgatherer.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDataDto {
    long id;
    String login;
    String name;
    String type;
    String avatarUrl;
    String createdAt;
    double calculations;
}
