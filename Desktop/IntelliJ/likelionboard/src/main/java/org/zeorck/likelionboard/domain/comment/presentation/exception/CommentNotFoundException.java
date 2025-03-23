package org.zeorck.likelionboard.domain.comment.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

public class CommentNotFoundException extends CustomException {
    public CommentNotFoundException() {
        super(CommentExceptionCode.COMMENT_NOT_FOUND);
    }
}
