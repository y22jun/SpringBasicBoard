package org.zeorck.likelionboard.common.auth.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record MemberLoginRequest(
        @NotBlank(message = "이메일이 비어있습니다.")
        String email,

        @NotBlank(message = "비밀번호가 비어있습니다.")
        String password
) {
}
