package org.zeorck.likelionboard.common.auth.domain.jwt;

public record LoginResult(
        String accessToken,
        String refreshToken
) {
}
