package org.zeorck.likelionboard.domain.board.presentation.request;

public record BoardUpdateRequest(
        String title,
        String content
) {
}
