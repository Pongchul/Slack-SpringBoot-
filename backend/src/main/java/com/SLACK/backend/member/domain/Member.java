package com.SLACK.backend.member.domain;

import com.SLACK.backend.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.ToString;
import org.hibernate.annotations.Type;

@Entity
@Getter
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

    public void deleted() {
        email = null;
        password = null;
        nickname = null;
        deleted = true;
    }





}
