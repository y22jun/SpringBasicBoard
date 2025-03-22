package org.zeorck.likelionboard.domain.board.infrastructure;

import org.zeorck.likelionboard.domain.board.domain.Board;

public interface BoardRepository {

    void save(Board board);
}
