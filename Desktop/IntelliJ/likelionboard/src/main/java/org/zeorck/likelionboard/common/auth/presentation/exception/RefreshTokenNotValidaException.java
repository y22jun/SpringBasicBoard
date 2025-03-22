package org.zeorck.likelionboard.common.auth.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

import static org.zeorck.likelionboard.common.auth.presentation.exception.AuthExceptionCode.REFRESH_TOKEN_NOT_VALID;

public class RefreshTokenNotValidaException extends CustomException {
    public RefreshTokenNotValidaException() {
        super(REFRESH_TOKEN_NOT_VALID);
    }
}
