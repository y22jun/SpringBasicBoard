package org.zeorck.likelionboard.domain.saveboard.infrastructure;

import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.saveboard.domain.SaveBoard;

public interface SaveBoardRepository {

    void save(SaveBoard saveBoard);

    SaveBoard findByMemberAndBoard(Member member, Board board);

    void deleteByMemberAndBoard(Member member, Board board);

}
