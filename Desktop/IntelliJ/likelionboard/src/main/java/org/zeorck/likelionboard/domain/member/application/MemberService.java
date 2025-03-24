package org.zeorck.likelionboard.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.member.infrastructure.MemberRepository;
import org.zeorck.likelionboard.domain.member.presentation.exception.EmailAlreadyExistsException;
import org.zeorck.likelionboard.domain.member.presentation.exception.NicknameAlreadyExistsException;
import org.zeorck.likelionboard.domain.member.presentation.response.MemberNicknameUpdateResponse;
import org.zeorck.likelionboard.domain.member.presentation.response.MemberSaveResponse;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(MemberSaveResponse memberSaveResponse) {
        validateSignUp(memberSaveResponse);

        Member member = Member.builder()
                .email(memberSaveResponse.email())
                .password(passwordEncoder.encode(memberSaveResponse.password()))
                .nickname(memberSaveResponse.nickname())
                .build();

        memberRepository.save(member);
    }

    @Transactional
    public void updateNickname(Long memberId, MemberNicknameUpdateResponse memberNicknameUpdateResponse) {
        Member member = getMemberId(memberId);
        member.updateNickname(memberNicknameUpdateResponse.nickname());
    }

    public Member getMemberId(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public void validateSignUp(MemberSaveResponse memberSaveResponse) {
        validateEmail(memberSaveResponse.email());
        validateNickname(memberSaveResponse.nickname());
    }

    private void validateEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException();
        }
    }

    private void validateNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new NicknameAlreadyExistsException();
        }
    }

}
