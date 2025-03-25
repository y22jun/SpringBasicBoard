package org.zeorck.likelionboard.domain.comment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.domain.board.application.BoardService;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.comment.domain.Comment;
import org.zeorck.likelionboard.domain.comment.infrastructure.CommentRepository;
import org.zeorck.likelionboard.domain.comment.presentation.exception.CommentDeleteForbidden;
import org.zeorck.likelionboard.domain.comment.presentation.exception.CommentUpdateForbidden;
import org.zeorck.likelionboard.domain.comment.presentation.response.CommentInfoResponse;
import org.zeorck.likelionboard.domain.comment.presentation.response.CommentSaveResponse;
import org.zeorck.likelionboard.domain.comment.presentation.response.CommentUpdateResponse;
import org.zeorck.likelionboard.domain.member.application.MemberService;
import org.zeorck.likelionboard.domain.member.domain.Member;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final BoardService boardService;

    public void save(Long memberId, Long boardId, CommentSaveResponse commentSaveResponse) {
        Member member = memberService.getMemberId(memberId);
        Board board = boardService.getBoardId(boardId);

        Comment comment = Comment.builder()
                .member(member)
                .board(board)
                .content(commentSaveResponse.content())
                .build();

        commentRepository.save(comment);
    }

    @Transactional
    public void update(Long memberId, Long commentId, CommentUpdateResponse commentUpdateResponse) {
        Member member = memberService.getMemberId(memberId);
        Comment comment = commentRepository.findById(commentId);

        validateUpdateForbidden(comment, member);

        comment.updateContent(commentUpdateResponse.content());
    }

    public void delete(Long memberId, Long commentId) {
        Member member = memberService.getMemberId(memberId);
        Comment comment = commentRepository.findById(commentId);

        validateDeleteForbidden(comment, member);

        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentInfoResponse> getCommentsByBoardId(Long boardId) {
        Board board = boardService.getBoardId(boardId);
        List<Comment> comments = commentRepository.findByBoard(board);

        return comments.stream()
                .map(CommentInfoResponse::from)
                .toList();
    }

    //member를 넘겨줄 이유가 없다.
    private void validateUpdateForbidden(Comment comment, Member member) {
        if (!comment.getMember().getId().equals(member.getId())) {
            throw new CommentUpdateForbidden();
        }
    }

    private void validateDeleteForbidden(Comment comment, Member member) {
        if (!comment.getMember().getId().equals(member.getId())) {
            throw new CommentDeleteForbidden();
        }
    }

}
