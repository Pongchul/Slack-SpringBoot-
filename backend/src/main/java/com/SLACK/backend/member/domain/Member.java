package com.SLACK.backend.member.domain;

import com.SLACK.backend.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Entity
@Getter
@Slf4j
@NoArgsConstructor
@Table(name = "tb_member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Nickname nickname;

    @Column(nullable = false)
    private boolean deleted;

    public Member(Email email, Password password, Nickname nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public Member(Long id) {
        this.id = id;
    }


    public Member(Email email, Password password) {
        this.email = email;
        this.password = password;
    }

    public void deleted() {
        email = null;
        password = null;
        nickname = null;
        deleted = true;
    }

    public String getEmail() {
        return Optional.ofNullable(email)
                .map(Email::getValue)
                .orElse("");
    }

    public String getPassword() {
        return Optional.ofNullable(password)
                .map(Password::getValue)
                .orElse("");
    }

    public String getNickname() {
        return Optional.ofNullable(nickname)
                .map(Nickname::getValue)
                .orElse("");
    }



}
