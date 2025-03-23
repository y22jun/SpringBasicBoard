package org.zeorck.likelionboard.domain.comment.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeorck.likelionboard.common.annotation.MemberId;
import org.zeorck.likelionboard.domain.comment.application.CommentService;
import org.zeorck.likelionboard.domain.comment.presentation.response.CommentSaveResponse;

@RestController
@RequestMapping("/comments/{boardId}")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> save(@MemberId Long memberId,
                                  @PathVariable("boardId") Long boardId,
                                  @RequestBody CommentSaveResponse commentSaveResponse) {
        commentService.save(memberId, boardId, commentSaveResponse);
        return new ResponseEntity<>(commentSaveResponse, HttpStatus.CREATED);
    }
}
