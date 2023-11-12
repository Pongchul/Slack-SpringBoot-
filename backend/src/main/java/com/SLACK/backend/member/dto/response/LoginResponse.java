package com.SLACK.backend.member.dto.response;

import com.SLACK.backend.member.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoginResponse {

    @NotNull
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String nickname;

    public static LoginResponse toResponse(Member member) {
        return new LoginResponse(member.getId(), member.getEmail(), member.getNickname());
    }
}
