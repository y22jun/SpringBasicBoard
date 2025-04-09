package org.zeorck.likelionboard.domain.board.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

public class BoardNotForbiddenException extends CustomException {
    public BoardNotForbiddenException() {
        super(BoardExceptionCode.BOARD_NOT_FORBIDDEN);
    }
}
