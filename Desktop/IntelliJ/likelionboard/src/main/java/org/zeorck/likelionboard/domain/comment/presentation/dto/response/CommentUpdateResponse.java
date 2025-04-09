package org.zeorck.likelionboard.domain.comment.presentation.dto.response;

import lombok.Builder;

@Builder
public record CommentUpdateResponse(
        Long commentId
) {
}
