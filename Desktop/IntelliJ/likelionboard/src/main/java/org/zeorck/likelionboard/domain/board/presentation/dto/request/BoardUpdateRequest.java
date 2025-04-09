package org.zeorck.likelionboard.domain.board.presentation.dto.request;

public record BoardUpdateRequest(
        String title,
        String content
) {
}
