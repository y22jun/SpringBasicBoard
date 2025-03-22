package org.zeorck.likelionboard.common.auth.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeorck.likelionboard.common.auth.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findFirstByMemberIdOrderByIdDesc(Long id);
}
