package org.zeorck.likelionboard.domain.heart.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.heart.domain.Heart;
import org.zeorck.likelionboard.domain.member.domain.Member;

@Repository
@RequiredArgsConstructor
public class HeartRepositoryImpl implements HeartRepository{

    private final HeartJpeRepository heartJpeRepository;

    @Override
    public void save(Heart heart) {
        heartJpeRepository.save(heart);
    }

    @Override
    public Heart findByMemberAndBoard(Member member, Board board) {
        return heartJpeRepository.findByMemberAndBoard(member, board);
    }

    @Override
    public int countByBoardAndStatusTrue(Board board) {
        return heartJpeRepository.countByBoardAndStatusTrue(board);
    }

    @Override
    public boolean existsByMemberAndBoard(Member member, Board board) {
        return heartJpeRepository.existsByMemberAndBoard(member, board);
    }

}
