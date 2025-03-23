package org.zeorck.likelionboard.domain.heart.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.heart.domain.Heart;
import org.zeorck.likelionboard.domain.member.domain.Member;

public interface HeartJpeRepository extends JpaRepository<Heart, Long> {

    @Query("SELECT h FROM Heart h "
            + "WHERE h.member = :member AND h.board = :board")
    Heart findByMemberAndBoard(@Param("member") Member member, @Param("board") Board board);

    @Query("UPDATE Heart h "
            + " SET h.status = :status "
            + " WHERE h.id = :id")
    @Modifying(clearAutomatically = true)
    void updateStatus(@Param("id") Long heartId, @Param("status") boolean b);

}
