package com.SLACK.backend.auth.dto;

public record AccessTokenResponse(
        String accessToken
) {

    public static AccessTokenResponse from(String accessToken) {
        return new AccessTokenResponse(accessToken);
    }
}
