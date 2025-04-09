package org.zeorck.likelionboard.domain.comment.presentation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.zeorck.likelionboard.common.exception.ExceptionCode;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum CommentExceptionCode implements ExceptionCode {
    COMMENT_NOT_FOUND(NOT_FOUND, "해당 댓글을 찾을 수 없습니다."),
    COMMENT_NOT_FORBIDDEN(FORBIDDEN, "해당 댓글에 접근할 권한이 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }
}
