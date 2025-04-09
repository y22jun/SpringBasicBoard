package org.zeorck.likelionboard.domain.heart.infrastructure;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.heart.domain.Heart;
import org.zeorck.likelionboard.domain.member.domain.Member;

public interface HeartJpeRepository extends JpaRepository<Heart, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select h from Heart h where h.member = :member and h.board = :board")
    Heart findByMemberAndBoard(Member member, Board board);

    int countByBoardAndStatusTrue(Board board);

}
