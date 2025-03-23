package org.zeorck.likelionboard.domain.saveboard.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeorck.likelionboard.common.annotation.MemberId;
import org.zeorck.likelionboard.common.response.PageableResponse;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardInfoResponse;
import org.zeorck.likelionboard.domain.saveboard.application.SaveBoardService;

@RestController
@RequestMapping("/save-boards")
@RequiredArgsConstructor
public class SaveBoardController {

    private final SaveBoardService saveBoardService;

    @PostMapping("/{boardId}")
    public ResponseEntity<?> toggleSaveBoard(@MemberId Long memberId,@PathVariable Long boardId) {
        saveBoardService.toggleBoard(memberId, boardId);
        return ResponseEntity.ok("게시글 북마크 상태가 변경되었습니다.");
    }

    @GetMapping
    public ResponseEntity<PageableResponse<BoardInfoResponse>> getSaveBoards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @MemberId Long memberId
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageableResponse<BoardInfoResponse> boardResponses = saveBoardService.getSaveBoards(memberId, pageable);
        return ResponseEntity.ok(boardResponses);
    }
}
