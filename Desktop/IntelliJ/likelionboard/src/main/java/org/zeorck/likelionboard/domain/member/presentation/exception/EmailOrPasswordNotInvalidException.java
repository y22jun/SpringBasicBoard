package org.zeorck.likelionboard.domain.member.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

public class EmailOrPasswordNotInvalidException extends CustomException {
    public EmailOrPasswordNotInvalidException() {
        super(MemberExceptionCode.EMAIL_OR_PASSWORD_NOT_INVALID);
    }
}
