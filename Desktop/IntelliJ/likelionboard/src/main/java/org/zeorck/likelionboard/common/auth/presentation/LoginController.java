package org.zeorck.likelionboard.common.auth.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zeorck.likelionboard.common.annotation.MemberId;
import org.zeorck.likelionboard.common.auth.application.AuthService;
import org.zeorck.likelionboard.common.auth.domain.jwt.LoginResult;
import org.zeorck.likelionboard.domain.member.presentation.response.MemberLoginRequest;

@RestController
@RequiredArgsConstructor
@Tag(name = "로그인 / 로그아웃", description = "로그인 / 로그아웃 관리 API")
public class LoginController {

    private final AuthService authService;

    //TODO: AuthService login 로직에 쿠키 로직 넣지말고 LoginSuccessHandler 클래스 만들어서 분리하기.
    @Operation(summary = "로그인", description = "로그인 기능을 수행합니다.")
    @ApiResponse(responseCode = "200")
    @PostMapping("/login")
    public ResponseEntity<LoginResult> login(@RequestBody MemberLoginRequest memberLoginRequest, HttpServletResponse response) {
        LoginResult result = authService.login(memberLoginRequest.email(), memberLoginRequest.password(), response);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //TODO: /members/logout -> logout으로 변경해보기. 아마도 SecurityConfig에서 설정을 해보자.
    //TODO: null vs delete 알아보기
    //TODO: LogoutHandler 알아보기
    @Operation(summary = "로그아웃", description = "로그아웃 기능을 수행합니다.")
    @ApiResponse(responseCode = "200")
    @PostMapping("/members/logout")
    public ResponseEntity<Void> logout(@MemberId Long memberId) {
        authService.logout(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
