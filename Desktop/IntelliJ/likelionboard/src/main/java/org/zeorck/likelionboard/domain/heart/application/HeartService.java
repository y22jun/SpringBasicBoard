package org.zeorck.likelionboard.domain.heart.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.domain.board.application.BoardService;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.board.infrastructure.BoardRepository;
import org.zeorck.likelionboard.domain.heart.domain.Heart;
import org.zeorck.likelionboard.domain.heart.infrastructure.HeartRepository;
import org.zeorck.likelionboard.domain.member.application.MemberService;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.member.infrastructure.MemberRepository;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void toggleHeart(Long boardId, Long memberId) {
        Member member = getMemberId(memberId);
        Board board = getBoardId(boardId);

        Heart heart = heartRepository.findByMemberAndBoard(member, board);

        if (heart == null) {
            addHeart(memberId, boardId);
        } else {
            heart.updateStatus(!heart.isStatus());
        }

    }

    private void addHeart(Long memberId, Long boardId) {
        Member member = getMemberId(memberId);
        Board board = getBoardId(boardId);

        Heart heart = Heart.builder()
                .member(member)
                .board(board)
                .status(true)
                .build();

        heartRepository.save(heart);
    }

    private Member getMemberId(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private Board getBoardId(Long boardId) {
        return boardRepository.findByBoardId(boardId);
    }

}
