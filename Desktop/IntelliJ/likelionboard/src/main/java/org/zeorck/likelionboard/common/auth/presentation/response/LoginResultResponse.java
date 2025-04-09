package org.zeorck.likelionboard.common.auth.presentation.response;

public record LoginResultResponse(
        String accessToken,
        String refreshToken
) {
}
