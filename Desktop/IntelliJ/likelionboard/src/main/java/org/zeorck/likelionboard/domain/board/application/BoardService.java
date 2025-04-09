package org.zeorck.likelionboard.domain.board.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.common.response.PageableResponse;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.board.infrastructure.BoardRepository;
import org.zeorck.likelionboard.domain.board.presentation.exception.BoardNotForbiddenException;
import org.zeorck.likelionboard.domain.board.presentation.request.BoardSaveRequest;
import org.zeorck.likelionboard.domain.board.presentation.request.BoardUpdateRequest;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardInfoResponse;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardSaveResponse;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardUpdateResponse;
import org.zeorck.likelionboard.domain.heart.infrastructure.HeartRepository;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.member.infrastructure.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final HeartRepository heartRepository;

    public BoardSaveResponse save(Long memberId, BoardSaveRequest boardSaveRequest) {
        Member member = getMemberId(memberId);
        Board board = Board.builder()
                .member(member)
                .title(boardSaveRequest.title())
                .content(boardSaveRequest.content())
                .build();

        boardRepository.save(board);

        return BoardSaveResponse.builder()
                .boardId(board.getId())
                .build();
    }

    @Transactional
    public BoardUpdateResponse update(Long memberId, Long boardId, BoardUpdateRequest boardUpdateRequest) {
        Board board = getBoardId(boardId);

        Long boardMemberId = board.getMember().getId();
        validateForbidden(memberId, boardMemberId);

        board.updateTitle(boardUpdateRequest.title());
        board.updateContent(boardUpdateRequest.content());

        return BoardUpdateResponse.builder()
                .boardId(boardId)
                .build();
    }

    @Transactional
    public void delete(Long memberId, Long boardId) {
        Board board = getBoardId(boardId);

        Long boardMemberId = board.getMember().getId();
        validateForbidden(memberId, boardMemberId);

        boardRepository.delete(board);
    }

    @Transactional
    public BoardInfoResponse getBoardInfo(Long boardId) {
        boardRepository.incrementViewCount(boardId);
        Board board = getBoardId(boardId);

        int heartCount = getHeartCount(board);

        return BoardInfoResponse.from(board, heartCount);
    }

    @Transactional(readOnly = true)
    public PageableResponse<BoardInfoResponse> getBoards(Pageable pageable) {
        Page<Board> boardPage = boardRepository.findAll(pageable);

        List<BoardInfoResponse> boardResponses = boardPage.getContent().stream()
                .map(board -> {
                    int heartCount = getHeartCount(board);
                    return BoardInfoResponse.from(board, heartCount);
                })
                .toList();

        return PageableResponse.of(pageable, boardResponses);
    }

    private Member getMemberId(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private Board getBoardId(Long boardId) {
        return boardRepository.findByBoardId(boardId);
    }

    private int getHeartCount(Board board) {
        return heartRepository.countByBoardAndStatusTrue(board);
    }

    private void validateForbidden(Long memberId, Long boardMemberId) {
        if (!boardMemberId.equals(memberId)) {
            throw new BoardNotForbiddenException();
        }
    }

}

