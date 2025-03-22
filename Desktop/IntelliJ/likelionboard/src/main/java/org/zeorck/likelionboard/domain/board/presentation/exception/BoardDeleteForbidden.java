package org.zeorck.likelionboard.domain.board.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

public class BoardDeleteForbidden extends CustomException {
    public BoardDeleteForbidden() {
        super(BoardExceptionCode.BOARD_DELETE_FORBIDDEN);
    }
}
