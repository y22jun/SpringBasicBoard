package org.zeorck.likelionboard.domain.saveboard.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.zeorck.likelionboard.common.domain.BaseTimeEntity;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.member.domain.Member;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public SaveBoard(Member member, Board board) {
        this.member = member;
        this.board = board;
    }

}
