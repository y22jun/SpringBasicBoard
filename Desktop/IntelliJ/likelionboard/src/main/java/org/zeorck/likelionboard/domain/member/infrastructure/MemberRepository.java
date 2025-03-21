package org.zeorck.likelionboard.domain.member.infrastructure;

import org.zeorck.likelionboard.domain.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);
}
