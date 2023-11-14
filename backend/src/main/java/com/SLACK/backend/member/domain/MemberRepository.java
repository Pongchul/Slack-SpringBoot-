package com.SLACK.backend.member.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MemberRepository extends Repository<Member, Long> {

    Member save(Member member);

    Optional<Member> findByEmail(Email email);

    Optional<Member> findById(Long member);

    Optional<Member> findByPassword(Password password);

    boolean existsByEmail(Email email);

    boolean existsByNickname(Nickname nickname);


}
