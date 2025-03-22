package org.zeorck.likelionboard.common.auth.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

import static org.zeorck.likelionboard.common.auth.presentation.exception.AuthExceptionCode.AUTHENTICATION_REQUIRED;

public class AuthenticationRequiredException extends CustomException {
    public AuthenticationRequiredException() {
        super(AUTHENTICATION_REQUIRED);
    }
}
