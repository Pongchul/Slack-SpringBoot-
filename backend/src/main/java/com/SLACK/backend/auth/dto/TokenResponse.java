package com.SLACK.backend.auth.dto;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {

    public static TokenResponse of(TokenRequest request, ) {
        return new TokenResponse()
    }
}
