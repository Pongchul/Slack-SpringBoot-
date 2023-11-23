package com.SLACK.backend.member.controller;

import com.SLACK.backend.member.domain.Email;
import com.SLACK.backend.member.domain.Member;
import com.SLACK.backend.member.dto.request.LoginRequest;
import com.SLACK.backend.member.dto.request.SignUpRequest;
import com.SLACK.backend.member.dto.response.LoginResponse;
import com.SLACK.backend.member.dto.response.MemberResponse;
import com.SLACK.backend.member.service.MemberService;
import com.fasterxml.jackson.annotation.JsonProperty;
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


    @GetMapping
    public ResponseEntity<MemberResponse> loginInfo(@RequestBody LoginRequest request) {
        Member member = memberService.findMemberByEmail(Email.from(request.getEmail()));
        MemberResponse response = MemberResponse.toResponse(member);

        return ResponseEntity.ok().body(response);
    }

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
        memberService.login(request);
        LoginResponse response = LoginResponse.toResponse(memberService.findMemberByEmail(Email.from(request.getEmail())));

        return ResponseEntity.ok().body(response);
    }




}
