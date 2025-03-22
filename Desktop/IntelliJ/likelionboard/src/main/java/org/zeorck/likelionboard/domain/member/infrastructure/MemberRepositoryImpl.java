package org.zeorck.likelionboard.domain.member.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.member.presentation.exception.MemberNotFoundException;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public void save(Member member) {
        memberJpaRepository.save(member);
    }

    @Override
    public Optional<Member> findById(Long userId) {
        return memberJpaRepository.findById(userId);
    }

    @Override
    public Member findByEmail(String email) {
        return memberJpaRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return memberJpaRepository.findByNickname(nickname);
    }

    @Override
    public boolean existsByEmail(String email) {
        return memberJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return memberJpaRepository.existsByNickname(nickname);
    }

}
