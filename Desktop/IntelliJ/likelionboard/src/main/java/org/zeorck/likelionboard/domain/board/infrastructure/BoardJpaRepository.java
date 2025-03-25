package org.zeorck.likelionboard.domain.board.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.domain.board.domain.Board;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {

    //TODO: 다른 방법으로도 구현해보기.
    @Modifying
    @Transactional
    @Query("update Board b set b.views = b.views + 1 where b.id = :id")
    void updateViews(@Param("id") Long id);
}
