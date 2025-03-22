package org.zeorck.likelionboard.domain.board.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeorck.likelionboard.common.annotation.MemberId;
import org.zeorck.likelionboard.common.response.PageableResponse;
import org.zeorck.likelionboard.domain.board.application.BoardService;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardInfoResponse;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardSaveResponse;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardUpdateResponse;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<PageableResponse<BoardInfoResponse>> getBoards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        PageableResponse<BoardInfoResponse> boardResponses = boardService.getBoards(pageable);
        return ResponseEntity.ok(boardResponses);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@MemberId Long memberId, @RequestBody BoardSaveResponse boardSaveResponse) {
        boardService.save(memberId, boardSaveResponse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("{boardId}")
    public ResponseEntity<?> update(@PathVariable Long boardId,
                                    @MemberId Long memberId,
                                    @RequestBody BoardUpdateResponse boardUpdateResponse) {
        boardService.update(memberId, boardId, boardUpdateResponse);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{boardId}")
    public ResponseEntity<?> delete(@PathVariable Long boardId,
                                    @MemberId Long memberId) {
        boardService.delete(memberId, boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
