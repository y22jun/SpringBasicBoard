package org.zeorck.likelionboard.domain.member.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

public class EmailAlreadyExistsException extends CustomException {
    public EmailAlreadyExistsException() {
        super(MemberExceptionCode.EMAIL_ALREADY_EXISTS);
    }
}
