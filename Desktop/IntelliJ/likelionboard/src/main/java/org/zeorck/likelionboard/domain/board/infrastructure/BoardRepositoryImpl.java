package org.zeorck.likelionboard.domain.board.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.zeorck.likelionboard.domain.board.domain.Board;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;

    public void save(Board board) {
        boardJpaRepository.save(board);
    }
}
