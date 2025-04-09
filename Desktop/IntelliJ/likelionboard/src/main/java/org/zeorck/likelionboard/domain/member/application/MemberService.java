package org.zeorck.likelionboard.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.member.infrastructure.MemberRepository;
import org.zeorck.likelionboard.domain.member.presentation.exception.EmailAlreadyExistsException;
import org.zeorck.likelionboard.domain.member.presentation.exception.NicknameAlreadyExistsException;
import org.zeorck.likelionboard.domain.member.presentation.request.MemberSaveRequest;
import org.zeorck.likelionboard.domain.member.presentation.response.MemberNicknameUpdateResponse;
import org.zeorck.likelionboard.domain.member.presentation.response.MemberSaveResponse;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberSaveResponse save(MemberSaveRequest memberSaveRequest) {
        validateSignUp(memberSaveRequest);

        Member member = Member.builder()
                .email(memberSaveRequest.email())
                .password(passwordEncoder.encode(memberSaveRequest.password()))
                .nickname(memberSaveRequest.nickname())
                .build();

        memberRepository.save(member);

        return MemberSaveResponse.builder()
                .memberId(member.getId())
                .build();
    }

    @Transactional
    public void updateNickname(Long memberId, MemberNicknameUpdateResponse memberNicknameUpdateResponse) {
        Member member = memberRepository.findById(memberId);
        member.updateNickname(memberNicknameUpdateResponse.nickname());
    }

    private void validateSignUp(MemberSaveRequest memberSaveRequest) {
        validateEmail(memberSaveRequest.email());
        validateNickname(memberSaveRequest.nickname());
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
