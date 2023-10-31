package com.SLACK.backend.member.domain;

import com.SLACK.backend.member.exception.MemberException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

import static com.SLACK.backend.member.exception.MemberErrorCode.*;
import static com.SLACK.backend.member.exception.MemberErrorCode.PASSWORD_IS_NOT_COLLECT_PATTERN;

@Getter
@Embeddable
@NoArgsConstructor
public class Password {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 15;
    private static final String PASSWORD_FORMAT = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_FORMAT);


    @Column(name = "password")
    private String value;

    public Password(String value) {
        this.value = value;
    }

    public static Password encrypt(String value, PasswordEncoder encoder) {
        validLengthInRange(value);
        validPatternIsValid(value);
        return new Password(encoder.encode(value));
    }

    public Password update(String value, PasswordEncoder encoder) {
        return Password.encrypt(value, encoder);
    }

    public boolean isSame(String password) {
        return value.equals(password);
    }

    private static void validLengthInRange(String value) {
        int length = value.length();
        if (length > MAX_LENGTH || length < MIN_LENGTH) {
            throw new MemberException(PASSWORD_IS_WRONG_LENGTH);
        }
    }

    private static boolean isNotValid(String value) {
        return !PASSWORD_PATTERN.matcher(value).matches();
    }

    private static void validPatternIsValid(String value) {
        if (isNotValid(value)) {
            throw new MemberException(PASSWORD_IS_NOT_COLLECT_PATTERN);
        }
    }

}
