package org.zeorck.likelionboard.domain.comment.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeorck.likelionboard.domain.comment.domain.Comment;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
}
