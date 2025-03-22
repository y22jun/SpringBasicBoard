package org.zeorck.likelionboard.domain.member.presentation.exception;

import org.zeorck.likelionboard.common.exception.CustomException;

public class MemberNotFoundException extends CustomException {
    public MemberNotFoundException() {
        super(MemberExceptionCode.MEMBER_NOT_FOUND);
    }
}
