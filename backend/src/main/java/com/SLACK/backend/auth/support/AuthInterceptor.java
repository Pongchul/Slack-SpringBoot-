package com.SLACK.backend.auth.support;

import com.SLACK.backend.auth.Authenticated;
import com.SLACK.backend.auth.domain.RefreshTokenRepository;
import com.SLACK.backend.auth.exception.AuthErrorCode;
import com.SLACK.backend.auth.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Optional;

import static com.SLACK.backend.auth.exception.AuthErrorCode.*;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        if (isPreflightRequest(request)) {
            return true;
        }
        Optional<Authenticated> authenticated = parseAnnotation((HandlerMethod) handler, Authenticated.class);
        if (authenticated.isPresent()) {
            validateToken(request);
        }


        String accessToken = BearerTokenExtractor.extract(request);
        jwtProvider.validateTokenNotUsable(accessToken);
        return true;


    }

    private <T extends Annotation> Optional<T> parseAnnotation(HandlerMethod handler, Class<T> clazz) {
        return Optional.ofNullable(handler.getMethodAnnotation(clazz));
    }

    private void validateToken(HttpServletRequest request) {
        String token = SlackTokenExtractor.extract(request);
        if (jwtProvider.validateTokenNotUsable(token)) {
            throw new AuthException(TOKEN_IS_EXPIRED);
        }
    }

    private boolean isPreflightRequest(HttpServletRequest request) {
        return isOptions(request) && hasHeaders(request) && hasMethod(request) && hasOrigin(request);
    }

    private boolean isOptions(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.toString());
    }

    private boolean hasHeaders(HttpServletRequest request) {
        return Objects.nonNull(request.getHeader("Access-Control-Request-Headers"));
    }

    private boolean hasMethod(HttpServletRequest request) {
        return Objects.nonNull(request.getHeader("Access-Control-Request-Method"));
    }

    private boolean hasOrigin(HttpServletRequest request) {
        return Objects.nonNull(request.getHeader("Origin"));
    }
}
