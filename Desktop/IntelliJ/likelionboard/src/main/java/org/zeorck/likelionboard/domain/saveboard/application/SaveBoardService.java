package org.zeorck.likelionboard.domain.saveboard.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.common.response.PageableResponse;
import org.zeorck.likelionboard.domain.board.application.BoardService;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardInfoResponse;
import org.zeorck.likelionboard.domain.member.application.MemberService;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.saveboard.domain.SaveBoard;
import org.zeorck.likelionboard.domain.saveboard.infrastructure.SaveBoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveBoardService {

    private final SaveBoardRepository saveBoardRepository;
    private final MemberService memberService;
    private final BoardService boardService;
    private final HeartReadService heartReadService;

    @Transactional
    public void toggleBoard(Long memberId, Long boardId) {
        Member member = memberService.getMemberId(memberId);
        Board board = boardService.getBoardId(boardId);

        //exists
        SaveBoard existsSaveBoard = saveBoardRepository.findByMemberAndBoard(member, board);
        if (existsSaveBoard == null) {
            saveBoard(member, board);
        } else {
            deleteSaveBoard(member, board);
        }
    }

    private void saveBoard(Member member, Board board) {

        SaveBoard saveBoard = SaveBoard.builder()
                .member(member)
                .board(board)
                .build();

        saveBoardRepository.save(saveBoard);
    }

    private void deleteSaveBoard(Member member, Board board) {
        saveBoardRepository.deleteByMemberAndBoard(member, board);
    }

    @Transactional(readOnly = true)
    public PageableResponse<BoardInfoResponse> getSaveBoards(Long memberId, Pageable pageable) {
        Member member = memberService.getMemberId(memberId);

        Page<SaveBoard> saveBoardPage = saveBoardRepository.findByMember(member, pageable);

        List<BoardInfoResponse> boardResponses = saveBoardPage.getContent().stream()
                .map(saveBoard -> {
                    Board board = saveBoard.getBoard();
                    int heartCount = heartReadService.getHeartCountByBoard(board);
                    return BoardInfoResponse.from(board, heartCount);
                })
                .toList();

        return PageableResponse.of(pageable, boardResponses);
    }

}
