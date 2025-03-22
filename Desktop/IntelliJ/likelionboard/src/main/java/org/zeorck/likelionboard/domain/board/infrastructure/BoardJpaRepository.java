package org.zeorck.likelionboard.domain.board.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeorck.likelionboard.domain.board.domain.Board;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {


}
