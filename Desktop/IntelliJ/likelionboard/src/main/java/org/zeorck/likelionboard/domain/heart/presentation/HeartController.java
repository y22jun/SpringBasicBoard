package org.zeorck.likelionboard.domain.heart.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@Tag(name = "게시글 좋아요", description = "게시글 좋아요 관리 API")
public class HeartController {

    private final HeartService heartService;

    @Operation(
            summary = "게시글 좋아요 추가 / 삭제", description = "토글 방식으로 게시글 좋아요 추가 / 삭제를 합니다.")
    @ApiResponse(responseCode = "200")
    @PostMapping("/{boardId}")
    public ResponseEntity<?> addLike(
            @PathVariable Long boardId,
            @MemberId Long memberId) {
        heartService.toggleHeart(boardId, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
