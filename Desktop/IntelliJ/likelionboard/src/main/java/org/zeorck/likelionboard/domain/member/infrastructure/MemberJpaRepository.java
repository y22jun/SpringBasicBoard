package org.zeorck.likelionboard.domain.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeorck.likelionboard.domain.member.domain.Member;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);
}
