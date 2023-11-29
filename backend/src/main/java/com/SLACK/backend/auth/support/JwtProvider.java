package com.SLACK.backend.auth.support;

import com.SLACK.backend.auth.exception.AuthException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.UUID;

import static com.SLACK.backend.auth.exception.AuthErrorCode.*;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class JwtProvider {

    private final SecretKey key;
    private final long accessTokenExpirationTime;
    private final long refreshTokenExpirationTime;

    public JwtProvider(@Value("${jwt.secret-key}") final String secretKey,
                       @Value("${jwt.access-token-expiration-time}") final long accessTokenExpirationTime,
                       @Value("${jwt.refresh-token-expiration-time}") final long refreshTokenExpirationTime) {
        this.key = hmacShaKeyFor(secretKey.getBytes(UTF_8));
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    public String createAccessToken(Long memberId) {
        return createToken(memberId.toString(), accessTokenExpirationTime, key);
    }

    private String createToken(String payload, long expireLength, SecretKey key) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expireLength);
        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken() {
        return createToken(UUID.randomUUID().toString(), refreshTokenExpirationTime, key);
    }

    public String getPayload(String token) {
        return validateParseJws(token).getBody().getSubject();
    }

    public Jws<Claims> validateParseJws(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new AuthException(TOKEN_IS_EXPIRED);
        } catch (JwtException e) {
            throw new AuthException(INVALID_TOKEN);
        }
    }
}
