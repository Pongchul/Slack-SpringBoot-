package com.SLACK.backend.member.service;

import com.SLACK.backend.member.domain.*;
import com.SLACK.backend.member.dto.request.SignUpRequest;
import com.SLACK.backend.member.exception.MemberErrorCode;
import com.SLACK.backend.member.exception.MemberException;
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

        validateEmailIsNotDuplicated(email);
        validateNicknameIsNotDuplicated(nickname);

        Member member = new Member(email, password, nickname);
        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

    @Transactional
    public void deleteId(Long id) {
        Member member = this.findMember(id);
        member.deleted();
    }

    private void validateEmailIsNotDuplicated(Email email) {
        if (memberRepository.existsByEmail(email)) {
            throw new MemberException(MemberErrorCode.SIGNUP_EMAIL_DUPLICATED);
        }
    }

    private void validateNicknameIsNotDuplicated(Nickname nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new MemberException(MemberErrorCode.SIGNUP_NICKNAME_DUPLICATED);
        }
    }

    public Member findMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_EXIST));
        validateExistMember(member);
        return member;
    }

    private void validateExistMember(Member member) {
        if (member.isDeleted()) {
            throw new MemberException(MemberErrorCode.MEMBER_DELETED);
        }
    }

}
