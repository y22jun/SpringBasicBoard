package org.zeorck.likelionboard.domain.comment.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

public class CommentDeleteForbidden extends CustomException {
    public CommentDeleteForbidden() {
        super(CommentExceptionCode.COMMENT_DELETE_FORBIDDEN);
    }
}
