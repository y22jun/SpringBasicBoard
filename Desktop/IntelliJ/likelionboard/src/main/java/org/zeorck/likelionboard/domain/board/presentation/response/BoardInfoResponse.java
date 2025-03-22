package org.zeorck.likelionboard.domain.board.presentation.response;

import lombok.Builder;
import org.zeorck.likelionboard.domain.board.domain.Board;

@Builder
public record BoardInfoResponse(
        Long boardId,
        String nickname,
        String title,
        String content,
        int views
) {
    public static BoardInfoResponse from(Board board) {
        return new BoardInfoResponse(
                board.getId(),
                board.getMember().getNickname(),
                board.getTitle(),
                board.getContent(),
                board.getViews()
        );
    }
}
