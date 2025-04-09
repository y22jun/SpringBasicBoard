package org.zeorck.likelionboard.domain.board.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record BoardSaveRequest(
        @NotBlank(message = "제목을 입력해주세요.")
        String title,

        @NotBlank(message = "내용을 입력해주새요.")
        String content
) {
}
