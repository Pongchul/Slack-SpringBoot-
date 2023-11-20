package com.SLACK.backend.directmessage.domain;

import com.SLACK.backend.directmessage.exception.DirectMessageErrorCode;
import com.SLACK.backend.directmessage.exception.DirectMessageException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Content {

    private static final int MAXIMUM_LENGTH = 255;

    @Lob
    @Column(name = "content")
    private String value;

    public Content(String value) {
        validateLengthInRange(value);
        this.value = value;
    }

    private void validateLengthInRange(String value) {
        int length = value.length();
        if (length > MAXIMUM_LENGTH) {
            throw new DirectMessageException(DirectMessageErrorCode.DIRECT_MESSAGE_IS_OUT_OF_RANGE);
        }
    }
}
