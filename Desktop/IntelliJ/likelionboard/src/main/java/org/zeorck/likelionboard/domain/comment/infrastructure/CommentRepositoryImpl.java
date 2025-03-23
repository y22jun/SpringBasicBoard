package org.zeorck.likelionboard.domain.comment.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.comment.domain.Comment;
import org.zeorck.likelionboard.domain.comment.presentation.exception.CommentNotFoundException;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public void save(Comment comment) {
        commentJpaRepository.save(comment);
    }

    @Override
    public Comment findById(Long commentId) {
        return commentJpaRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public void delete(Comment comment) {
        commentJpaRepository.delete(comment);
    }

    @Override
    public List<Comment> findByBoard(Board board) {
        return commentJpaRepository.findByBoard(board);
    }
}
