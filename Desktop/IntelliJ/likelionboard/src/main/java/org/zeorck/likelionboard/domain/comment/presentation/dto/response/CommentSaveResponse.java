package org.zeorck.likelionboard.domain.comment.presentation.dto.response;

import lombok.Builder;

@Builder
public record CommentSaveResponse(
        Long commentId
) {
}
