package org.zeorck.likelionboard.domain.board.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeorck.likelionboard.common.annotation.MemberId;
import org.zeorck.likelionboard.common.response.PageableResponse;
import org.zeorck.likelionboard.domain.board.application.BoardService;
import org.zeorck.likelionboard.domain.board.presentation.request.BoardSaveRequest;
import org.zeorck.likelionboard.domain.board.presentation.request.BoardUpdateRequest;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardInfoResponse;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardSaveResponse;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardUpdateResponse;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Tag(name = "게시판", description = "게시판 관리 API")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "특정 게시글 조회", description = "특정 게시글을 조회합니다.")
    @ApiResponse(responseCode = "200")
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardInfoResponse> getBoard(@PathVariable Long boardId) {
        BoardInfoResponse boardInfoResponse = boardService.getBoardInfo(boardId);
        return new ResponseEntity<>(boardInfoResponse, HttpStatus.OK);
    }

    @Operation(summary = "전체 게시글 조회", description = "전체 게시글을 조회합니다.")
    @ApiResponse(responseCode = "200")
    @GetMapping
    public ResponseEntity<PageableResponse<BoardInfoResponse>> getBoards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageableResponse<BoardInfoResponse> boardResponses = boardService.getBoards(pageable);
        return new ResponseEntity<>(boardResponses, HttpStatus.OK);
    }

    @Operation(summary = "게시글 저장", description = "게시글을 저장합니다.")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<BoardSaveResponse> save(
            @MemberId Long memberId,
            @Valid @RequestBody BoardSaveRequest boardSaveRequest
            ) {
        BoardSaveResponse boardSaveResponse = boardService.save(memberId, boardSaveRequest);
        return new ResponseEntity<>(boardSaveResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "특정 게시글 수정", description = "특정 게시글을 수정합니다.")
    @ApiResponse(responseCode = "200")
    @PatchMapping("{boardId}")
    public ResponseEntity<BoardUpdateResponse> update(
            @PathVariable Long boardId,
            @MemberId Long memberId,
            @RequestBody BoardUpdateRequest boardUpdateRequest
    ) {
        BoardUpdateResponse boardUpdateResponse = boardService.update(memberId, boardId, boardUpdateRequest);
        return new ResponseEntity<>(boardUpdateResponse, HttpStatus.OK);
    }

    @Operation(summary = "특정 게시글 삭제", description = "특정 게시글을 삭제합니다.")
    @ApiResponse(responseCode = "204")
    @DeleteMapping("{boardId}")
    public ResponseEntity<?> delete(
            @PathVariable Long boardId,
            @MemberId Long memberId) {
        boardService.delete(memberId, boardId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
