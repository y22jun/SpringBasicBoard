package org.zeorck.likelionboard.domain.comment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.domain.board.application.BoardService;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.board.infrastructure.BoardRepository;
import org.zeorck.likelionboard.domain.comment.domain.Comment;
import org.zeorck.likelionboard.domain.comment.infrastructure.CommentRepository;
import org.zeorck.likelionboard.domain.comment.presentation.exception.CommentDeleteForbidden;
import org.zeorck.likelionboard.domain.comment.presentation.exception.CommentUpdateForbidden;
import org.zeorck.likelionboard.domain.comment.presentation.response.CommentInfoResponse;
import org.zeorck.likelionboard.domain.comment.presentation.response.CommentSaveResponse;
import org.zeorck.likelionboard.domain.comment.presentation.response.CommentUpdateResponse;
import org.zeorck.likelionboard.domain.member.application.MemberService;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.member.infrastructure.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public void save(Long memberId, Long boardId, CommentSaveResponse commentSaveResponse) {
        Member member = getMemberId(memberId);
        Board board = getBoardId(boardId);

        Comment comment = Comment.builder()
                .member(member)
                .board(board)
                .content(commentSaveResponse.content())
                .build();

        commentRepository.save(comment);
    }

    @Transactional
    public void update(Long memberId, Long commentId, CommentUpdateResponse commentUpdateResponse) {
        Comment comment = getCommentId(commentId);

        Long commentMemberId = comment.getMember().getId();

        validateUpdateForbidden(commentMemberId, memberId);

        comment.updateContent(commentUpdateResponse.content());
    }

    @Transactional
    public void delete(Long memberId, Long commentId) {
        Comment comment = getCommentId(commentId);

        Long commentMemberId = comment.getMember().getId();

        validateDeleteForbidden(commentMemberId, memberId);

        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentInfoResponse> getCommentsByBoardId(Long boardId) {
        Board board = getBoardId(boardId);
        List<Comment> comments = commentRepository.findByBoard(board);

        return comments.stream()
                .map(CommentInfoResponse::from)
                .toList();
    }

    private Member getMemberId(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private Board getBoardId(Long boardId) {
        return boardRepository.findByBoardId(boardId);
    }

    private Comment getCommentId(Long commentId) {
        return commentRepository.findById(commentId);
    }

    private void validateUpdateForbidden(Long commentMemberId, Long memberId) {
        if (!commentMemberId.equals(memberId)) {
            throw new CommentUpdateForbidden();
        }
    }

    private void validateDeleteForbidden(Long commentMemberId, Long memberId) {
        if (!commentMemberId.equals(memberId)) {
            throw new CommentDeleteForbidden();
        }
    }

}
