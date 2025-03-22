package org.zeorck.likelionboard.domain.board.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

public class BoardNotFoundException extends CustomException {
    public BoardNotFoundException() {
        super(BoardExceptionCode.BOARD_NOT_FOUND);
    }
}
