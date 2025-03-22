package org.zeorck.likelionboard.domain.board.infrastructure;

import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardInfoResponse;

import java.util.List;

public interface BoardRepository {

    void save(Board board);

    Board findByBoardId(Long boardId);

    void delete(Board board);

    List<Board> findAll();

}
