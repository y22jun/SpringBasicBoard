package org.zeorck.likelionboard.domain.board.infrastructure;

import org.zeorck.likelionboard.domain.board.domain.Board;

public interface BoardRepository {

    void save(Board board);

    Board findByBoardId(Long boardId);

    void delete(Board board);
}
