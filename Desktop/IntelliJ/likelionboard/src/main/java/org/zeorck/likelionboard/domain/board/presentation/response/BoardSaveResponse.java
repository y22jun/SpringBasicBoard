package org.zeorck.likelionboard.domain.board.presentation.response;

import lombok.Builder;

@Builder
public record BoardSaveResponse(
        Long boardId
) {
}
