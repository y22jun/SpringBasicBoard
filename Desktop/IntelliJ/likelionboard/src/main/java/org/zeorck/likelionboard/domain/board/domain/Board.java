package org.zeorck.likelionboard.domain.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.zeorck.likelionboard.common.domain.BaseTimeEntity;
import org.zeorck.likelionboard.domain.board.presentation.exception.BoardDeleteForbidden;
import org.zeorck.likelionboard.domain.board.presentation.exception.BoardUpdateForbidden;
import org.zeorck.likelionboard.domain.member.domain.Member;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String content;

    @Column(columnDefinition = "integer default 0")
    private int views;

    @Builder
    private Board(Member member, String title, String content, int views) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.views = views;
    }

    public void updateTitle(String title) {
        if (title != null && !title.isBlank()) {
            this.title = title;
        }
    }

    public void updateContent(String content) {
        if (content != null && !content.isBlank()) {
            this.content = content;
        }
    }

    public void incrementViews(int views) {
        this.views++;
    }

}
