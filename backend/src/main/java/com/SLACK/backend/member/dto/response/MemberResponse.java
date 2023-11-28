package com.SLACK.backend.member.dto.response;

import com.SLACK.backend.member.domain.Member;
import com.SLACK.backend.workspace.domain.Workspace;
import com.SLACK.backend.workspace.dto.response.WorkspaceResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberResponse {

    private Long id;
    private String nickname;
    private String email;

    public static MemberResponse toResponse(Member member) {
        return new MemberResponse(member.getId(), member.getNickname(), member.getEmail());
    }
}
