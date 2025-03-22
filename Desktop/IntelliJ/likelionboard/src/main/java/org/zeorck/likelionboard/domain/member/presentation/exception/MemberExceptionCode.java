package org.zeorck.likelionboard.domain.member.presentation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.zeorck.likelionboard.common.exception.ExceptionCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum MemberExceptionCode implements ExceptionCode {
    MEMBER_NOT_FOUND(NOT_FOUND, "유저를 찾을 수 없습니다."),
    EMAIL_ALREADY_EXISTS(BAD_REQUEST, "이메일이 이미 존재합니다."),
    NICKNAME_ALREADY_EXISTS(BAD_REQUEST, "닉네임이 이미 존재합니다."),
    EMAIL_OR_PASSWORD_NOT_INVALID(BAD_REQUEST, "이메일 또는 비밀번호가 올바르지 않습니다."),
    ;

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }
}
