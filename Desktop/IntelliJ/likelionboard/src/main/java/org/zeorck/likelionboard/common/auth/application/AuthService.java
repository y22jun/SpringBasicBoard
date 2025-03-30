package org.zeorck.likelionboard.common.auth.application;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.common.auth.application.jwt.TokenInjector;
import org.zeorck.likelionboard.common.auth.domain.RefreshToken;
import org.zeorck.likelionboard.common.auth.domain.jwt.LoginResult;
import org.zeorck.likelionboard.common.auth.domain.jwt.TokenGenerator;
import org.zeorck.likelionboard.common.auth.infrastructure.RefreshTokenRepository;
import org.zeorck.likelionboard.common.auth.infrastructure.jwt.TokenProperties;
import org.zeorck.likelionboard.domain.member.application.MemberService;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.member.infrastructure.MemberRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenGenerator tokenGenerator;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProperties tokenProperties;
    private final PasswordEncoder passwordEncoder;
    private final TokenInjector tokenInjector;

    @Transactional
    public LoginResult login(String email, String password, HttpServletResponse response) {
        Member member = memberRepository.findByEmail(email);
        member.checkPassword(passwordEncoder, password);

        LoginResult result = generateLoginResult(member);

        tokenInjector.injectTokensToCookie(result, response);

        return result;
    }

    @Transactional
    public void logout(Long memberId) {
        refreshTokenRepository.deleteByMemberId(memberId);
    }

    private LoginResult generateLoginResult(Member member) {
        Long memberId = member.getId();
        String accessToken = tokenGenerator.generateAccessToken(memberId);
        String refreshToken = tokenGenerator.generateRefreshToken(memberId);

        RefreshToken refreshTokenEntity = refreshTokenRepository.findByMemberId(memberId)
                .orElse(RefreshToken.of(member.getId(), refreshToken, tokenProperties.expirationTime().refreshToken()));

        refreshTokenEntity.rotate(refreshToken);
        refreshTokenRepository.save(refreshTokenEntity);

        return new LoginResult(accessToken, refreshToken);
    }
}
