package org.zeorck.likelionboard.domain.member.presentation.response;

import jakarta.validation.constraints.NotBlank;

public record MemberNicknameUpdateResponse(

        @NotBlank(message = "닉네임을 입력해주세요.")
        String nickname
) {
}
