package org.zeorck.likelionboard.domain.comment.infrastructure;

import org.zeorck.likelionboard.domain.comment.domain.Comment;

public interface CommentRepository {

    void save(Comment comment);
}
