package com.SLACK.backend.member.domain;

import com.SLACK.backend.member.exception.MemberException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.SLACK.backend.member.exception.MemberErrorCode.*;

@Getter
@Embeddable
@NoArgsConstructor
public class Email {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 30;
    private static final String EMAIL_FORMAT = "@";

    @Column(name = "email", unique = true)
    private String value;

    public Email(String value) {
        this.value = value;
    }

    public static Email from(String value) {
        validLengthInRange(value);
        validEmailFormat(value);
        return new Email(value);
    }

    private static void validLengthInRange(String value) {
        int length = value.length();
        if (length > MAX_LENGTH || length < MIN_LENGTH) {
            throw new MemberException(EMAIL_IS_WRONG_LENGTH);
        }
    }

    private static void validEmailFormat(String value) {
        if (!value.contains(EMAIL_FORMAT)) {
            throw new MemberException(EMAIL_IS_WRONG_FORMAT);
        }
    }
}
