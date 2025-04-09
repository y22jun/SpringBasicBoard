package org.zeorck.likelionboard.domain.member.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record MemberNicknameUpdateRequest(
        @NotBlank(message = "닉네임을 입력해주세요.")
        String nickname
) {
}
