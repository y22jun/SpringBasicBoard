package org.zeorck.likelionboard.domain.board.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zeorck.likelionboard.domain.board.domain.Board;

public interface BoardRepository {

    void save(Board board);

    Board findByBoardId(Long boardId);

    void delete(Board board);

    Page<Board> findAll(Pageable pageable);

    void updateViews(Long id);

}
