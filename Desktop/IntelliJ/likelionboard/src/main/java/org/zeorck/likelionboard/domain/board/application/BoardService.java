package org.zeorck.likelionboard.domain.board.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.common.response.PageableResponse;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.board.infrastructure.BoardRepository;
import org.zeorck.likelionboard.domain.board.presentation.exception.BoardDeleteForbidden;
import org.zeorck.likelionboard.domain.board.presentation.exception.BoardUpdateForbidden;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardInfoResponse;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardSaveResponse;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardUpdateResponse;
import org.zeorck.likelionboard.domain.heart.application.HeartReadService;
import org.zeorck.likelionboard.domain.member.application.MemberService;
import org.zeorck.likelionboard.domain.member.domain.Member;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;
    private final HeartReadService heartReadService;

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
        Board board = getBoardId(boardId);
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
        Board board = getBoardId(boardId);
        validateDeleteForbidden(board, member);

        boardRepository.delete(board);
    }

    @Transactional
    public BoardInfoResponse getBoardInfo(Long id) {
        Board board = getBoardId(id);
        boardRepository.updateViews(id);

        int heartCount = heartReadService.getHeartCountByBoard(board);

        return BoardInfoResponse.from(board, heartCount);
    }

    @Transactional(readOnly = true)
    public PageableResponse<BoardInfoResponse> getBoards(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAll(pageable);

        List<BoardInfoResponse> boardResponses = boardPage.getContent().stream()
                .map(board -> {
                    int heartCount = heartReadService.getHeartCountByBoard(board);
                    return BoardInfoResponse.from(board, heartCount);
                })
                .toList();

        return PageableResponse.of(pageable, boardResponses);
    }

    public Board getBoardId(Long boardId) {
        return boardRepository.findByBoardId(boardId);
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

