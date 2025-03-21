package org.zeorck.likelionboard.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.member.infrastructure.MemberRepository;
import org.zeorck.likelionboard.domain.member.presentation.response.MemberSaveResponse;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberSaveResponse memberSaveResponse) {

        Member member = Member.builder()
                .email(memberSaveResponse.email())
                .password(memberSaveResponse.password())
                .nickname(memberSaveResponse.nickname())
                .build();

        memberRepository.save(member);
    }
}
