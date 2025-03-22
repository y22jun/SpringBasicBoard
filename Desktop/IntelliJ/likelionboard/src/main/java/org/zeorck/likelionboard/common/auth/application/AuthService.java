package org.zeorck.likelionboard.common.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.common.auth.domain.RefreshToken;
import org.zeorck.likelionboard.common.auth.domain.jwt.LoginResult;
import org.zeorck.likelionboard.common.auth.domain.jwt.TokenGenerator;
import org.zeorck.likelionboard.common.auth.infrastructure.RefreshTokenRepository;
import org.zeorck.likelionboard.common.auth.infrastructure.jwt.TokenProperties;
import org.zeorck.likelionboard.domain.member.application.MemberService;
import org.zeorck.likelionboard.domain.member.domain.Member;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;
    private final TokenGenerator tokenGenerator;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProperties tokenProperties;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public LoginResult login(String email, String password) {
        Member member = memberService.findByEmail(email);
        member.checkPassword(passwordEncoder, password);

        return generateLoginResult(member);
    }

    private LoginResult generateLoginResult(Member member) {
        String accessToken = tokenGenerator.generateAccessToken(member.getId());
        String refreshToken = tokenGenerator.generateRefreshToken(member.getId());

        RefreshToken refreshTokenEntity = refreshTokenRepository.findByMemberId(member.getId())
                .orElse(RefreshToken.of(member.getId(), refreshToken, tokenProperties.expirationTime().refreshToken()));

        refreshTokenEntity.rotate(refreshToken);
        refreshTokenRepository.save(refreshTokenEntity);

        return new LoginResult(accessToken, refreshToken);
    }
}
