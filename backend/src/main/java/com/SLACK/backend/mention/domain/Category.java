package com.SLACK.backend.mention.domain;

import com.SLACK.backend.mention.exception.MentionErrorCode;
import com.SLACK.backend.mention.exception.MentionException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Category {

    CHAT(1,"채팅"),
    DM(2, "디엠"),
    SYSTEM(3,"시스템")
    ;

    private final long id;
    private final String name;

    public static Category from(Long id) {
        return Arrays.stream(values())
                .filter(category -> category.id == id)
                .findFirst()
                .orElseThrow(() -> new MentionException(MentionErrorCode.CATEGORY_NOT_EXIST));
    }
}
