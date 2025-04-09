package org.zeorck.likelionboard.domain.heart.infrastructure;

import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.heart.domain.Heart;
import org.zeorck.likelionboard.domain.member.domain.Member;

import java.util.Optional;

public interface HeartRepository {

    void save(Heart heart);

    Heart findByMemberAndBoard(Member member, Board board);

    int countByBoardAndStatusTrue(Board board);

}
