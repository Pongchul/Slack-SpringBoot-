package com.SLACK.backend.auth.dto;

public record TokenRequest(
        String accessToken,
        String refreshToken
) {
    public static TokenRequest of(String accessToken, String refreshToken) {
        return new TokenRequest(accessToken, refreshToken);
    }
}
