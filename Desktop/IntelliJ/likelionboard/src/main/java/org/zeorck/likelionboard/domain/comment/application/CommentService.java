package org.zeorck.likelionboard.domain.comment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zeorck.likelionboard.domain.board.application.BoardService;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.comment.domain.Comment;
import org.zeorck.likelionboard.domain.comment.infrastructure.CommentRepository;
import org.zeorck.likelionboard.domain.comment.presentation.response.CommentSaveResponse;
import org.zeorck.likelionboard.domain.member.application.MemberService;
import org.zeorck.likelionboard.domain.member.domain.Member;

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
}
