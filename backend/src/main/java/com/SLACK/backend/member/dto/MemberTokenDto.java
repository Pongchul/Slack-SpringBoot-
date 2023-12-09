package com.SLACK.backend.member.dto;

import com.SLACK.backend.member.domain.Member;

public record MemberTokenDto(Long id, String email, String nickName, String token) {

    public static MemberTokenDto from(Member member, String token) {
        return new MemberTokenDto(member.getId(), member.getEmail(), member.getNickname(), token);
    }
}
