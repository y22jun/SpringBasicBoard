package org.zeorck.likelionboard.domain.heart.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.heart.domain.Heart;
import org.zeorck.likelionboard.domain.member.domain.Member;

public interface HeartJpeRepository extends JpaRepository<Heart, Long> {

    Heart findByMemberAndBoard(Member member, Board board);

    boolean existsByMemberAndBoard(Member member, Board board);

    int countByBoardAndStatusTrue(Board board);

}
