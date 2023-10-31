package com.SLACK.backend.member.domain;

import com.SLACK.backend.member.exception.MemberErrorCode;
import com.SLACK.backend.member.exception.MemberException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Nickname {

    private static final int MINIMUM_LENGTH = 1;
    private static final int MAXIMUM_LENGTH = 20;

    @Column(name = "name", length = 20)
    private String value;

    private Nickname(String value) {
        this.value = value;
    }

    public static Nickname from(String value) {
        validateLengthInRange(value);
        return new Nickname(value);
    }

    public Nickname update(String value) {
        return Nickname.from(value);
    }


    private static void validateLengthInRange(String value) {
        int length = value.length();
        if (length < MINIMUM_LENGTH || MAXIMUM_LENGTH < length) {
            throw new MemberException(MemberErrorCode.NICKNAME_IS_WRONG_LENGTH);
        }
    }


}
