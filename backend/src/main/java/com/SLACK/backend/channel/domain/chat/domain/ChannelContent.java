package com.SLACK.backend.channel.domain.chat.domain;

import com.SLACK.backend.channel.exception.ChannelErrorCode;
import com.SLACK.backend.channel.exception.ChannelException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class ChannelContent {

    private static final int MAXIMUM_LENGTH = 255;

    @Lob
    @Column(name = "content")
    private String value;

    public ChannelContent(String value) {
        validateLengthInRange(value);
        this.value = value;
    }

    private void validateLengthInRange(String value) {
        int length = value.length();
        if (length > MAXIMUM_LENGTH) {
            throw new ChannelException(ChannelErrorCode.CHANNEL_NAME_CANNOT_BE_OUT_OF_RANGE);
        }
    }
}
