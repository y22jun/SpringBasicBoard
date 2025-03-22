package org.zeorck.likelionboard.domain.board.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.board.infrastructure.BoardRepository;
import org.zeorck.likelionboard.domain.board.presentation.exception.BoardDeleteForbidden;
import org.zeorck.likelionboard.domain.board.presentation.exception.BoardUpdateForbidden;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardInfoResponse;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardSaveResponse;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardUpdateResponse;
import org.zeorck.likelionboard.domain.member.application.MemberService;
import org.zeorck.likelionboard.domain.member.domain.Member;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void update(Long memberId, Long boardId, BoardUpdateResponse boardUpdateResponse) {
        Member member = memberService.getMemberId(memberId);
        Board board = boardRepository.findByBoardId(boardId);
        validateUpdateForbidden(board, member);

        if (boardUpdateResponse.title() != null) {
            board.updateTitle(boardUpdateResponse.title());
        }

        if (boardUpdateResponse.content() != null) {
            board.updateContent(boardUpdateResponse.content());
        }
    }

    public void delete(Long memberId, Long boardId) {
        Member member = memberService.getMemberId(memberId);
        Board board = boardRepository.findByBoardId(boardId);
        validateDeleteForbidden(board, member);

        boardRepository.delete(board);
    }

    @Transactional(readOnly = true)
    public List<BoardInfoResponse> getBoards() {
        return boardRepository.findAll().stream()
                .map(BoardInfoResponse::from)
                .collect(Collectors.toList());
    }

    private void validateUpdateForbidden(Board board, Member member) {
        if (!board.getMember().getId().equals(member.getId())) {
            throw new BoardUpdateForbidden();
        }
    }

    private void validateDeleteForbidden(Board board, Member member) {
        if (!board.getMember().getId().equals(member.getId())) {
            throw new BoardDeleteForbidden();
        }
    }

}

