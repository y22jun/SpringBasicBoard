package org.zeorck.likelionboard.domain.board.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.board.presentation.exception.BoardNotFoundException;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;

    public void save(Board board) {
        boardJpaRepository.save(board);
    }

    public Board findByBoardId(Long boardId) {
        return boardJpaRepository.findById(boardId)
                .orElseThrow(BoardNotFoundException::new);
    }

    public void delete(Board board) {
        boardJpaRepository.delete(board);
    }

    @Override
    public Page<Board> findAll(Pageable pageable) {
        return boardJpaRepository.findAll(pageable);
    }

}
