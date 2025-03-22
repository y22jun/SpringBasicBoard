package org.zeorck.likelionboard.domain.board.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

public class BoardUpdateForbidden extends CustomException {
    public BoardUpdateForbidden() {
        super(BoardExceptionCode.BOARD_UPDATE_FORBIDDEN);
    }
}
