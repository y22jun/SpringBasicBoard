package org.zeorck.likelionboard.domain.comment.presentation.response;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateResponse(
        @NotBlank(message = "댓글을 입력해주세요.")
        String content) {
}
