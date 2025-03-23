package org.zeorck.likelionboard.domain.comment.presentation.response;

import org.zeorck.likelionboard.domain.comment.domain.Comment;

public record CommentInfoResponse(
        Long boardId,
        String nickname,
        String content
) {
    public static CommentInfoResponse from(Comment comment) {
        return new CommentInfoResponse(
                comment.getBoard().getId(),
                comment.getMember().getNickname(),
                comment.getContent()
        );
    }
}
