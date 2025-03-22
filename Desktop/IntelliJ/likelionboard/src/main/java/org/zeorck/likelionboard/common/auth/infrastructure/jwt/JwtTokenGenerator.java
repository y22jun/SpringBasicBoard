package org.zeorck.likelionboard.common.auth.infrastructure.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.zeorck.likelionboard.common.auth.domain.jwt.TokenGenerator;

import javax.crypto.SecretKey;
import java.util.Date;

import static io.jsonwebtoken.io.Decoders.BASE64;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator implements TokenGenerator {

    private final TokenProperties tokenProperties;

    @Override
    public String generateAccessToken(Long memberId) {
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);
        Date expiration = new Date(currentTimeMillis + tokenProperties.expirationTime().accessToken() * 1000);
        SecretKey secretKey = Keys.hmacShaKeyFor(BASE64.decode(tokenProperties.secretKey()));

        return Jwts.builder()
                .subject(String.valueOf(memberId))
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public String generateRefreshToken(Long memberId) {
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);
        SecretKey secretKey = Keys.hmacShaKeyFor(BASE64.decode(tokenProperties.secretKey()));

        return Jwts.builder()
                .subject(String.valueOf(memberId))
                .issuedAt(now)
                .signWith(secretKey)
                .compact();
    }

}
