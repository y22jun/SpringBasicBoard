package org.zeorck.likelionboard.domain.comment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.board.infrastructure.BoardRepository;
import org.zeorck.likelionboard.domain.comment.domain.Comment;
import org.zeorck.likelionboard.domain.comment.infrastructure.CommentRepository;
import org.zeorck.likelionboard.domain.comment.presentation.exception.CommentNotForbiddenException;
import org.zeorck.likelionboard.domain.comment.presentation.dto.request.CommentSaveRequest;
import org.zeorck.likelionboard.domain.comment.presentation.dto.request.CommentUpdateRequest;
import org.zeorck.likelionboard.domain.comment.presentation.dto.response.CommentInfoResponse;
import org.zeorck.likelionboard.domain.comment.presentation.dto.response.CommentSaveResponse;
import org.zeorck.likelionboard.domain.comment.presentation.dto.response.CommentUpdateResponse;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.member.infrastructure.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public CommentSaveResponse save(Long memberId, Long boardId, CommentSaveRequest commentSaveRequest) {
        Member member = getMemberId(memberId);
        Board board = getBoardId(boardId);

        Comment comment = Comment.builder()
                .member(member)
                .board(board)
                .content(commentSaveRequest.content())
                .build();

        commentRepository.save(comment);

        return CommentSaveResponse.builder()
                .commentId(comment.getId())
                .build();
    }

    @Transactional
    public CommentUpdateResponse update(Long memberId, Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Comment comment = getCommentId(commentId);

        Long commentMemberId = comment.getMember().getId();
        validateForbidden(memberId, commentMemberId);

        comment.updateContent(commentUpdateRequest.content());

        return CommentUpdateResponse.builder()
                .commentId(comment.getId())
                .build();
    }

    @Transactional
    public void delete(Long memberId, Long commentId) {
        Comment comment = getCommentId(commentId);

        Long commentMemberId = comment.getMember().getId();
        validateForbidden(memberId, commentMemberId);

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

    private void validateForbidden(Long memberId, Long commentMemberId) {
        if (!commentMemberId.equals(memberId)) {
            throw new CommentNotForbiddenException();
        }
    }

}
