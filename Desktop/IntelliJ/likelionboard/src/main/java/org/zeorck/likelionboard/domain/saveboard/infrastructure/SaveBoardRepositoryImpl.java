package org.zeorck.likelionboard.domain.saveboard.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.saveboard.domain.SaveBoard;

@Repository
@RequiredArgsConstructor
public class SaveBoardRepositoryImpl implements SaveBoardRepository {

    private final SaveBoardJpaRepository saveBoardJpaRepository;

    @Override
    public void save(SaveBoard saveBoard) {
        saveBoardJpaRepository.save(saveBoard);
    }

    @Override
    public SaveBoard findByMemberAndBoard(Member member, Board board) {
        return saveBoardJpaRepository.findByMemberAndBoard(member, board);
    }

    @Override
    public void deleteByMemberAndBoard(Member member, Board board) {
        saveBoardJpaRepository.deleteByMemberAndBoard(member, board);
    }

    @Override
    public Page<SaveBoard> findByMember(Member member, Pageable pageable) {
        return saveBoardJpaRepository.findByMember(member, pageable);
    }

    @Override
    public boolean existsByMemberAndBoard(Member member, Board board) {
        return saveBoardJpaRepository.existsByMemberAndBoard(member, board);
    }

}
