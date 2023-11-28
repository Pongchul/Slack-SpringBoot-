package com.SLACK.backend.member.domain;

import com.SLACK.backend.member.exception.MemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PasswordTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @DisplayName("패스워드는 8자 이상 15자 이하 여야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {" ", "a1!", "123456789asdfgh!@#$"})
    void passwordMustBeValidRule(String password) {
        assertThatThrownBy(() -> Password.encrypt(password, passwordEncoder))
                .isInstanceOf(MemberException.class)
                .hasMessage("패스워드는 8자 이상 15자 이하 여야 합니다.");
    }

    @DisplayName("사용자의 비밀번호는 올바른 패턴 형식이어야 한다")
    @ParameterizedTest
    @ValueSource(strings = {"1234567!", "asdfghj!", "asdf1234", "12345678", "asdfghjk", "!@#$%^&*"})
    void passwordMustValidPattern(String password) {
        assertThatThrownBy(() -> Password.encrypt(password, passwordEncoder))
                .isInstanceOf(MemberException.class)
                .hasMessage("패스워드는 영문자와 하나 이상의 숫자, 특수 문자를 갖고 있어야 합니다.");
    }


}