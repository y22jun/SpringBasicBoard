package org.zeorck.likelionboard.domain.heart.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.likelionboard.domain.board.domain.Board;
import org.zeorck.likelionboard.domain.heart.infrastructure.HeartRepository;

@Service
@RequiredArgsConstructor
public class HeartReadService {

    private final HeartRepository heartRepository;

    @Transactional(readOnly = true)
    public int getHeartCountByBoard(Board board) {
        return heartRepository.countByBoardAndStatusTrue(board);
    }

}
