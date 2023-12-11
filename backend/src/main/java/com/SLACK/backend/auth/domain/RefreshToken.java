package com.SLACK.backend.auth.domain;

import com.SLACK.backend.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.EqualsAndHashCode.*;

@Getter
@Entity
@Table(name = "tb_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class RefreshToken extends BaseTimeEntity {

    @Id
    @Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String token;

    public RefreshToken(Long memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }
}
