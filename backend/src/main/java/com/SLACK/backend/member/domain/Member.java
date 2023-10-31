package com.SLACK.backend.member.domain;

import com.SLACK.backend.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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

    public Member(Email email, Password password, Nickname nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }



}
