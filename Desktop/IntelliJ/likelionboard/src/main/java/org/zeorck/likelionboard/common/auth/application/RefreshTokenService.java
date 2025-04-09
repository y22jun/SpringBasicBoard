package org.zeorck.likelionboard.common.auth.application;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.common.auth.application.jwt.TokenInjector;
import org.zeorck.likelionboard.common.auth.domain.RefreshToken;
import org.zeorck.likelionboard.common.auth.presentation.response.LoginResultResponse;
import org.zeorck.likelionboard.common.auth.domain.jwt.TokenGenerator;
import org.zeorck.likelionboard.common.auth.domain.jwt.TokenResolver;
import org.zeorck.likelionboard.common.auth.infrastructure.RefreshTokenRepository;
import org.zeorck.likelionboard.common.auth.infrastructure.jwt.TokenProperties;
import org.zeorck.likelionboard.common.auth.presentation.exception.AuthenticationRequiredException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenGenerator tokenGenerator;
    private final TokenResolver tokenResolver;
    private final TokenInjector tokenInjector;
    private final TokenProperties tokenProperties;

    @Transactional
    public LoginResultResponse reissueBasedOnRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = tokenResolver.resolveRefreshTokenFromRequest(request)
                .orElseThrow(AuthenticationRequiredException::new);

        RefreshToken savedRefreshToken = validateAndGetSavedRefreshToken(refreshToken);

        return getReissuedTokenResult(response, savedRefreshToken);
    }

    private LoginResultResponse getReissuedTokenResult(HttpServletResponse response, RefreshToken savedRefreshToken) {
        Long memberId = savedRefreshToken.getMemberId();

        String reissuedAccessToken = tokenGenerator.generateAccessToken(memberId);
        String rotatedRefreshToken = this.rotate(savedRefreshToken);

        LoginResultResponse loginResult = new LoginResultResponse(
                reissuedAccessToken, rotatedRefreshToken);
        tokenInjector.injectTokensToCookie(loginResult, response);

        return loginResult;
    }

    private RefreshToken validateAndGetSavedRefreshToken(String refreshToken) {
        Long memberId = Long.valueOf(tokenResolver.getSubjectFromToken(refreshToken));
        RefreshToken savedRefreshToken = this.getByTokenString(refreshToken);

        savedRefreshToken.validateWith(refreshToken, memberId);

        return savedRefreshToken;
    }

    public RefreshToken getByTokenString(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(AuthenticationRequiredException::new);
    }

    private String rotate(RefreshToken refreshToken) {
        String reissuedToken = tokenGenerator.generateRefreshToken(refreshToken.getMemberId());
        refreshToken.rotate(reissuedToken);
        refreshToken.updateExpirationIfExpired(tokenProperties.expirationTime().refreshToken());
        refreshTokenRepository.save(refreshToken);

        return reissuedToken;
    }
}
