package org.zeorck.likelionboard.domain.board.presentation.response;

import lombok.Builder;
import org.zeorck.likelionboard.domain.board.domain.Board;

import java.time.LocalDate;

@Builder
public record BoardInfoResponse(
        Long boardId,
        String nickname,
        String title,
        String content,
        int views,
        int heartCount,
        LocalDate createdAt
) {
    public static BoardInfoResponse from(Board board, int heartCount) {
        return new BoardInfoResponse(
                board.getId(),
                board.getMember().getNickname(),
                board.getTitle(),
                board.getContent(),
                board.getViews(),
                heartCount,
                board.getCreatedAt().toLocalDate()
        );
    }
}
