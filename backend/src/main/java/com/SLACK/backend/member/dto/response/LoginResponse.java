package com.SLACK.backend.member.dto.response;

import com.SLACK.backend.auth.dto.TokenDto;
import com.SLACK.backend.member.domain.Member;

public record LoginResponse (

        String accessToken,
        String refreshToken,
        MemberResponse memberResponse

) {

    public static LoginResponse of(TokenDto tokenDto, Member member) {
        return new LoginResponse(tokenDto.accessToken(), tokenDto.refreshToken(), MemberResponse.toResponse(member));
    }
}
