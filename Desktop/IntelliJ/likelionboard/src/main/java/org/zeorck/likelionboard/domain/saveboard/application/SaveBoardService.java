package org.zeorck.likelionboard.domain.saveboard.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.domain.board.application.BoardService;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.member.application.MemberService;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.saveboard.domain.SaveBoard;
import org.zeorck.likelionboard.domain.saveboard.infrastructure.SaveBoardRepository;

@Service
@RequiredArgsConstructor
public class SaveBoardService {

    private final SaveBoardRepository saveBoardRepository;
    private final MemberService memberService;
    private final BoardService boardService;

    public void toggleBoard(Long memberId, Long boardId) {
        Member member = memberService.getMemberId(memberId);
        Board board = boardService.getBoardId(boardId);

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
}
