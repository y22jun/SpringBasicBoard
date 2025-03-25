package org.zeorck.likelionboard.common.exception;

import org.springframework.http.HttpStatus;

//Exception 한번 바꿔보기 인터셉터
public interface ExceptionCode {

    HttpStatus getStatus();

    String getCode();

    String getMessage();
}
