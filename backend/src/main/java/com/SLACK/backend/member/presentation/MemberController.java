package com.SLACK.backend.member.presentation;

import com.SLACK.backend.auth.Authenticated;
import com.SLACK.backend.auth.dto.TokenDto;
import com.SLACK.backend.auth.support.JwtProvider;
import com.SLACK.backend.member.domain.Member;
import com.SLACK.backend.member.dto.request.LoginRequest;
import com.SLACK.backend.member.dto.request.SignUpRequest;
import com.SLACK.backend.member.dto.response.LoginResponse;
import com.SLACK.backend.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;


    @PostMapping
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest request) {
        memberService.signUp(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id) {
        memberService.deleteId(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        TokenDto tokenDto = memberService.login(request);
        String memberId = jwtProvider.getPayload(tokenDto.accessToken());
        Member member = memberService.findMemberById(Long.valueOf(memberId));

        return ResponseEntity.ok(LoginResponse.of(tokenDto, member));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Authenticated Long memberId) {
        memberService.logout(memberId);

        log.info("TEST");
        return ResponseEntity.noContent().build();
    }
}
