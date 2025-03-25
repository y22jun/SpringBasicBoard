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

        //exists
        //JPA 메서드마다 성능차이 알아보기
        Heart existingHeart = heartRepository.findByMemberAndBoard(member, board);

        if (existingHeart == null) {
            addHeart(memberId, boardId);
        } else {
            existingHeart.updateStatus(!existingHeart.isStatus());
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
