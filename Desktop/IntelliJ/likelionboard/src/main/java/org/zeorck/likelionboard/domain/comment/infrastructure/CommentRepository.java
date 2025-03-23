package org.zeorck.likelionboard.domain.comment.infrastructure;

import org.zeorck.likelionboard.domain.comment.domain.Comment;

public interface CommentRepository {

    void save(Comment comment);

    Comment findById(Long commentId);

    void delete(Comment comment);
}
