package org.zeorck.likelionboard.domain.board.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.board.presentation.exception.BoardNotFoundException;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardInfoResponse;

import java.util.List;

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

    public List<Board> findAll() {
        return boardJpaRepository.findAll();
    }
}
