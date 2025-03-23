package org.zeorck.likelionboard.domain.heart.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zeorck.likelionboard.common.annotation.MemberId;
import org.zeorck.likelionboard.domain.heart.application.HeartService;

@RestController
@RequestMapping("/hearts")
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    // TODO: 상태코드 생각해보기.
    @PostMapping("/{boardId}")
    public ResponseEntity<?> addLike(@PathVariable Long boardId, @MemberId Long memberId) {
        heartService.toggleHeart(boardId, memberId);
        return ResponseEntity.ok("좋아요 상태가 변경되었습니다.");
    }

}
