package org.zeorck.likelionboard.domain.comment.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

public class CommentNotForbiddenException extends CustomException {
    public CommentNotForbiddenException() {
        super(CommentExceptionCode.COMMENT_NOT_FORBIDDEN);
    }
}
