package com.SLACK.backend.auth.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RefreshTokenCookieProvider {

    public static final String REFRESH_TOKEN = "refreshToken";
    private static final String VALID_COOKIE_PATH = "/";
    private static final String LOGOUT_COOKIE_VALUE = "";
    private static final int LOGOUT_COOKIE_AGE = 0;

    private final long expirationTime;

    public RefreshTokenCookieProvider(@Value("${jwt.refresh-token-expiration-time}") final long refreshTokenExpirationTime) {
        this.expirationTime = refreshTokenExpirationTime;
    }

    public ResponseCookie createCookie(String refreshToken) {
        return ResponseCookie.from(REFRESH_TOKEN, refreshToken)
                .sameSite("None")
                .domain(".zipgo.pet")
                .maxAge(Duration.ofMillis(expirationTime))
                .path(VALID_COOKIE_PATH)
                .secure(true)
                .httpOnly(true)
                .build();
    }

    public ResponseCookie createLogoutCookie() {
        return ResponseCookie.from(REFRESH_TOKEN, LOGOUT_COOKIE_VALUE)
                .sameSite("None")
                .domain(".zipgo.pet")
                .maxAge(LOGOUT_COOKIE_AGE)
                .build();
    }
}
