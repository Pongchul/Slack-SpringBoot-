package com.SLACK.backend.member.controller;

import com.SLACK.backend.member.dto.request.LoginRequest;
import com.SLACK.backend.member.dto.request.SignUpRequest;
import com.SLACK.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

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
    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {
        memberService.login(request);

        return ResponseEntity.ok().build();
    }


}
