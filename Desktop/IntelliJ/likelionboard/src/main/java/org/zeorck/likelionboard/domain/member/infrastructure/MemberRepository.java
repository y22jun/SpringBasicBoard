package org.zeorck.likelionboard.domain.member.infrastructure;

import org.zeorck.likelionboard.domain.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long userId);

    Member findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

}
