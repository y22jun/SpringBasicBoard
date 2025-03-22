package org.zeorck.likelionboard.common.auth.infrastructure.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.zeorck.likelionboard.common.auth.domain.RefreshToken;
import org.zeorck.likelionboard.common.auth.infrastructure.RefreshTokenJpaRepository;
import org.zeorck.likelionboard.common.auth.infrastructure.RefreshTokenRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenJpaRepository.findByToken(token);
    }

    @Override
    public Optional<RefreshToken> findByMemberId(Long id) {
        return refreshTokenJpaRepository.findFirstByMemberIdOrderByIdDesc(id);
    }

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenJpaRepository.save(refreshToken);
    }
}
