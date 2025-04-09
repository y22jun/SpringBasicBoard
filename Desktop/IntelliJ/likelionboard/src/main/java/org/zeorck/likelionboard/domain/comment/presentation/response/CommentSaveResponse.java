package org.zeorck.likelionboard.domain.comment.presentation.response;

import lombok.Builder;

@Builder
public record CommentSaveResponse(
        Long commentId
) {
}
