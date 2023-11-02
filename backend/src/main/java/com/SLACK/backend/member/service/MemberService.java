package com.SLACK.backend.member.service;

import com.SLACK.backend.member.domain.*;
import com.SLACK.backend.member.dto.request.SignUpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public Long signUp(SignUpRequest request) {
        Email email = Email.from(request.getEmail());
        Password password = Password.encrypt(request.getPassword(), passwordEncoder);
        Nickname nickname = Nickname.from(request.getNickname());
        Member member = new Member(email, password, nickname);
        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

}
