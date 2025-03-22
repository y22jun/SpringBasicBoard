package org.zeorck.likelionboard.common.auth.infrastructure;

import org.zeorck.likelionboard.common.auth.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {

    Optional<RefreshToken> findByToken(String token);

    void save(RefreshToken refreshToken);

    Optional<RefreshToken> findByMemberId(Long id);
}
