package org.zeorck.likelionboard.domain.comment.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.comment.domain.Comment;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoard(Board board);
}
