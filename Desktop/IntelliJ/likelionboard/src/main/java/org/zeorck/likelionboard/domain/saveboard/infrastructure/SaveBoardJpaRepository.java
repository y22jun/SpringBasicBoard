package org.zeorck.likelionboard.domain.saveboard.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.saveboard.domain.SaveBoard;

public interface SaveBoardJpaRepository extends JpaRepository<SaveBoard, Long> {

    SaveBoard findByMemberAndBoard(Member member, Board board);

    void deleteByMemberAndBoard(Member member, Board board);

    boolean existsByMemberAndBoard(Member member, Board board);

    Page<SaveBoard> findByMember(Member member, Pageable pageable);

}
