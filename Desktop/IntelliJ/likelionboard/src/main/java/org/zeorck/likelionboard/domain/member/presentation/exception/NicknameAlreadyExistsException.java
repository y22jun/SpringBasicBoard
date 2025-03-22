package org.zeorck.likelionboard.domain.member.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

public class NicknameAlreadyExistsException extends CustomException {
    public NicknameAlreadyExistsException() {
        super(MemberExceptionCode.NICKNAME_ALREADY_EXISTS);
    }
}
