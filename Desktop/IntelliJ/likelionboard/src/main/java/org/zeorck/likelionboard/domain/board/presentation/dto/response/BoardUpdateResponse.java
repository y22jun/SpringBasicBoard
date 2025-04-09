package org.zeorck.likelionboard.domain.board.presentation.dto.response;

import lombok.Builder;

@Builder
public record BoardUpdateResponse(
        Long boardId
) {
}
