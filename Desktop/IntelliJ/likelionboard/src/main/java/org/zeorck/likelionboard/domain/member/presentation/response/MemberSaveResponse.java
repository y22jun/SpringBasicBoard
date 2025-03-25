package org.zeorck.likelionboard.domain.member.presentation.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MemberSaveResponse(
        @NotBlank(message = "이메일은 필수 입력사항 입니다.")
        @Email(message = "이메일 형식이 아닙니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수 입력사항 입니다.")
        String password,

        @NotBlank(message = "닉네임은 필수 입력사항 입니다.")
        String nickname) {
}
