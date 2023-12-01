package com.SLACK.backend.member.presentation;

import com.SLACK.backend.auth.dto.TokenDto;
import com.SLACK.backend.auth.support.JwtProvider;
import com.SLACK.backend.member.domain.Email;
import com.SLACK.backend.member.domain.Member;
import com.SLACK.backend.member.dto.MemberTokenDto;
import com.SLACK.backend.member.dto.request.LoginRequest;
import com.SLACK.backend.member.dto.request.SignUpRequest;
import com.SLACK.backend.member.dto.response.LoginResponse;
import com.SLACK.backend.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.SLACK.backend.auth.support.SlackTokenExtractor.SLACK_HEADER;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;

//    @GetMapping
//    public ResponseEntity<MemberTokenDto> loginInfo(@RequestBody LoginRequest request) {
//        Member member = memberService.findMemberByEmail(Email.from(request.getEmail()));
//        String jwt = jwtProvider.createAccessToken(member.getId());
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(SLACK_HEADER,);
//
//        return ResponseEntity.ok().body();
//    }

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

}
