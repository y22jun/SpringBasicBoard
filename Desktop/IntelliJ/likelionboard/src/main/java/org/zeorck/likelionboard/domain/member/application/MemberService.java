package org.zeorck.likelionboard.domain.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.member.infrastructure.MemberRepository;
import org.zeorck.likelionboard.domain.member.presentation.exception.EmailAlreadyExistsException;
import org.zeorck.likelionboard.domain.member.presentation.exception.NicknameAlreadyExistsException;
import org.zeorck.likelionboard.domain.member.presentation.dto.request.MemberNicknameUpdateRequest;
import org.zeorck.likelionboard.domain.member.presentation.dto.request.MemberSaveRequest;
import org.zeorck.likelionboard.domain.member.presentation.dto.response.MemberNicknameUpdateResponse;
import org.zeorck.likelionboard.domain.member.presentation.dto.response.MemberSaveResponse;

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
    public MemberNicknameUpdateResponse updateNickname(Long memberId, MemberNicknameUpdateRequest memberNicknameUpdateRequest) {
        Member member = memberRepository.findById(memberId);
        member.updateNickname(memberNicknameUpdateRequest.nickname());

        return MemberNicknameUpdateResponse.builder()
                .memberId(member.getId())
                .build();
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
