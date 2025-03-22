package org.zeorck.likelionboard.common.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zeorck.likelionboard.common.auth.application.AuthService;
import org.zeorck.likelionboard.common.auth.domain.jwt.LoginResult;
import org.zeorck.likelionboard.domain.member.presentation.response.MemberLoginRequest;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResult> login(@RequestBody MemberLoginRequest memberLoginRequest) {
        LoginResult result = authService.login(memberLoginRequest.email(), memberLoginRequest.password());
        return ResponseEntity.ok(result);
    }

}
