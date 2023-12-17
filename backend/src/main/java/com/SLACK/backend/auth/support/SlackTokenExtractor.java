package com.SLACK.backend.auth.support;

import com.SLACK.backend.auth.exception.AuthErrorCode;
import com.SLACK.backend.auth.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class SlackTokenExtractor {

    public static final String SLACK_HEADER = "Refresh";
    private static final String SLACK_TYPE = "Slack ";
    private static final String SLACK_JWT_REGEX = "^Slack [A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$";

    public static String extract(HttpServletRequest request) {
        String authorization = request.getHeader(SLACK_HEADER);
        validate(authorization);
        return authorization.replace(SLACK_TYPE, "").trim();
    }

    private static void validate(String authorization) {
        if (authorization == null) {
            throw new AuthException(AuthErrorCode.NEED_TOKEN);
        }
        if (!authorization.matches(SLACK_JWT_REGEX)) {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }
    }
}
