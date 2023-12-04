package com.SLACK.backend.member.application;

import com.SLACK.backend.auth.domain.RefreshToken;
import com.SLACK.backend.auth.domain.RefreshTokenRepository;
import com.SLACK.backend.auth.dto.TokenDto;
import com.SLACK.backend.auth.support.JwtProvider;
import com.SLACK.backend.member.domain.*;
import com.SLACK.backend.member.dto.request.LoginRequest;
import com.SLACK.backend.member.dto.request.SignUpRequest;
import com.SLACK.backend.member.exception.MemberErrorCode;
import com.SLACK.backend.member.exception.MemberException;
import org.antlr.v4.runtime.Token;
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
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

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
        Member member = this.findMemberById(id);
        member.deleted();
    }

    @Transactional
    public TokenDto login(LoginRequest request) {
        Member member = memberRepository.findMemberByEmail(Email.from(request.getEmail()))
                .orElseThrow(() -> new MemberException(MemberErrorCode.EMAIL_IS_WRONG));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.PASSWORD_IS_WRONG_LENGTH);
        }

        String accessToken = jwtProvider.createAccessToken(member.getId());
        String refreshToken = jwtProvider.createRefreshToken();

        refreshTokenRepository.deleteByMemberId(member.getId());
        refreshTokenRepository.save(new RefreshToken(member.getId(), refreshToken));

        return TokenDto.of(accessToken, refreshToken);
    }

    public void logout(Long memberId) {
        refreshTokenRepository.deleteByMemberId(memberId);
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

    public Member findMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_EXIST));
        validateExistMember(member);
        return member;
    }

    public Member findMemberByEmail(Email email) {
        Member member = memberRepository.findByEmail(email)
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
