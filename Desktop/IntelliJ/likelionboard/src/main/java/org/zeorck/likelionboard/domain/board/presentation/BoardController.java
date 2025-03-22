package org.zeorck.likelionboard.domain.board.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeorck.likelionboard.common.annotation.MemberId;
import org.zeorck.likelionboard.domain.board.application.BoardService;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardSaveResponse;
import org.zeorck.likelionboard.domain.board.presentation.response.BoardUpdateResponse;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

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
}
