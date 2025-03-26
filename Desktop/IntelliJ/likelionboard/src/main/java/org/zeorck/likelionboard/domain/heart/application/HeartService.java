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
        Board board = boardRepository.findByBoardId(boardId);
        Member member = memberRepository.findById(memberId);

        Heart heart = heartRepository.findByMemberAndBoard(member, board);
        boolean existingHeart = heartRepository.existsByMemberAndBoard(member, board);

        if (!existingHeart) {
            addHeart(memberId, boardId);
        } else {
            heart.updateStatus(!heart.isStatus());
        }

    }

    private void addHeart(Long memberId, Long boardId) {
        Member member = memberRepository.findById(memberId);
        Board board = boardRepository.findByBoardId(boardId);

        Heart heart = Heart.builder()
                .member(member)
                .board(board)
                .status(true)
                .build();

        heartRepository.save(heart);
    }

}
