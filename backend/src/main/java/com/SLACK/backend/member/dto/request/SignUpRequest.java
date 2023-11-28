package com.SLACK.backend.member.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotNull
    private String email;

    @NotNull
    private String nickname;

    @NotNull
    private String password;
}
