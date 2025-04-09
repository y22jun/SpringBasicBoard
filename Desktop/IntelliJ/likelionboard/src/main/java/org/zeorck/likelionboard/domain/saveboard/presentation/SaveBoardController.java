package org.zeorck.likelionboard.domain.saveboard.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeorck.likelionboard.common.annotation.MemberId;
import org.zeorck.likelionboard.common.response.PageableResponse;
import org.zeorck.likelionboard.domain.board.presentation.dto.response.BoardInfoResponse;
import org.zeorck.likelionboard.domain.saveboard.application.SaveBoardService;

@RestController
@RequestMapping("/save-boards")
@RequiredArgsConstructor
@Tag(name = "게시글 북마크", description = "게시글 북마크 관리 API")
public class SaveBoardController {

    private final SaveBoardService saveBoardService;

    @Operation(summary = "게시글 북마크", description = "토글 방식으로 게시글 북마크 기능을 수행합니다.")
    @ApiResponse(responseCode = "200")
    @PostMapping("/{boardId}")
    public ResponseEntity<?> toggleSaveBoard(
            @MemberId Long memberId,
            @PathVariable Long boardId
    ) {
        saveBoardService.toggleBoard(memberId, boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "게시글 북마크 전체 조회", description = "북마크 한 모든 게시글을 조회합니다.")
    @ApiResponse(responseCode = "200")
    @GetMapping
    public ResponseEntity<PageableResponse<BoardInfoResponse>> getSaveBoards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @MemberId Long memberId
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageableResponse<BoardInfoResponse> boardResponses = saveBoardService.getSaveBoards(memberId, pageable);
        return new ResponseEntity<>(boardResponses, HttpStatus.OK);
    }
}
