package org.zeorck.likelionboard.domain.comment.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateRequest(
        @NotBlank(message = "댓글을 입력해주세요.")
        String content
) {
}
