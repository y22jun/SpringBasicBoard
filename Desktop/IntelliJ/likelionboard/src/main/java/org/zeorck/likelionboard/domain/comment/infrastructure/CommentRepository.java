package org.zeorck.likelionboard.domain.comment.infrastructure;

import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.comment.domain.Comment;

import java.util.List;

public interface CommentRepository {

    void save(Comment comment);

    Comment findById(Long commentId);

    void delete(Comment comment);

    List<Comment> findByBoard(Board board);
}
