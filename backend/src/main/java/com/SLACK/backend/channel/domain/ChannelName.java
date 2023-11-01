package com.SLACK.backend.channel.domain;

import com.SLACK.backend.channel.exception.ChannelErrorCode;
import com.SLACK.backend.channel.exception.ChannelException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class ChannelName {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 20;

    @Column(name = "name", nullable = true, length = 20)
    private String value;

    public ChannelName(String value) {
        this.value = value;
    }

    public static ChannelName from(String value) {
        validateIsNotBlank(value);
        validateLengthInRange(value);
        return new ChannelName(value);
    }

    public ChannelName update(String value) {
        return ChannelName.from(value);
    }

    private static void validateIsNotBlank(String value) {
        if (value.isBlank()) {
            throw new ChannelException(ChannelErrorCode.CHANNEL_NAME_IS_NOT_BLANK);
        }
    }

    private static void validateLengthInRange(String value) {
        int length = value.length();
        if (length > MAX_LENGTH || length < MIN_LENGTH) {
            throw new ChannelException(ChannelErrorCode.CHANNEL_NAME_CANNOT_BE_OUT_OF_RANGE);
        }
    }
}
