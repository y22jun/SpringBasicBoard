package org.zeorck.likelionboard.domain.board.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.board.infrastructure.BoardRepository;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardSaveResponse;
import org.zeorck.likelionboard.domain.member.application.MemberService;
import org.zeorck.likelionboard.domain.member.domain.Member;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    public void save(Long memberId, BoardSaveResponse boardSaveResponse) {
        Member member = memberService.getMemberId(memberId);
        Board board = Board.builder()
                .member(member)
                .title(boardSaveResponse.title())
                .content(boardSaveResponse.content())
                .build();

        boardRepository.save(board);
    }
}

