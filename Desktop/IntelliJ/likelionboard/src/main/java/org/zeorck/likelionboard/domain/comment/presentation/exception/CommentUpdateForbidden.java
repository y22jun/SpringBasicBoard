package org.zeorck.likelionboard.domain.comment.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

public class CommentUpdateForbidden extends CustomException {
    public CommentUpdateForbidden() {
        super(CommentExceptionCode.COMMENT_UPDATE_FORBIDDEN);
    }
}
