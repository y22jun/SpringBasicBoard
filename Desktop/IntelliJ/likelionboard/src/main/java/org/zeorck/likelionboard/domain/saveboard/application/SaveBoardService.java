package org.zeorck.likelionboard.domain.saveboard.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.common.response.PageableResponse;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.board.infrastructure.BoardRepository;
import org.zeorck.likelionboard.domain.board.presentation.dto.response.BoardInfoResponse;
import org.zeorck.likelionboard.domain.heart.infrastructure.HeartRepository;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.member.infrastructure.MemberRepository;
import org.zeorck.likelionboard.domain.saveboard.domain.SaveBoard;
import org.zeorck.likelionboard.domain.saveboard.infrastructure.SaveBoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveBoardService {

    private final SaveBoardRepository saveBoardRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final HeartRepository heartRepository;

    @Transactional
    public void toggleBoard(Long memberId, Long boardId) {
        Member member = getMemberId(memberId);
        Board board = getBoardId(boardId);

        boolean existsSaveBoard = saveBoardRepository.existsByMemberAndBoard(member, board);

        if (!existsSaveBoard) {
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

    //쿼리 DSL로 한번 작성해보자
    @Transactional(readOnly = true)
    public PageableResponse<BoardInfoResponse> getSaveBoards(Long memberId, Pageable pageable) {
        Member member = getMemberId(memberId);

        Page<SaveBoard> saveBoardPage = saveBoardRepository.findByMember(member, pageable);

        List<BoardInfoResponse> boardResponses = saveBoardPage.getContent().stream()
                .map(saveBoard -> {
                    Board board = saveBoard.getBoard();
                    int heartCount = heartRepository.countByBoardAndStatusTrue(board);
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

}
