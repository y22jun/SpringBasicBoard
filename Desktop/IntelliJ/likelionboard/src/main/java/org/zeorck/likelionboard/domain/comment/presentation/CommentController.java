package org.zeorck.likelionboard.domain.comment.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeorck.likelionboard.common.annotation.MemberId;
import org.zeorck.likelionboard.domain.comment.application.CommentService;
import org.zeorck.likelionboard.domain.comment.presentation.response.CommentSaveResponse;
import org.zeorck.likelionboard.domain.comment.presentation.response.CommentUpdateResponse;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{boardId}")
    public ResponseEntity<?> save(@MemberId Long memberId,
                                  @PathVariable("boardId") Long boardId,
                                  @RequestBody CommentSaveResponse commentSaveResponse) {
        commentService.save(memberId, boardId, commentSaveResponse);
        return new ResponseEntity<>(commentSaveResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> update(@MemberId Long memberId,
                                  @PathVariable("commentId") Long commentId,
                                  @RequestBody CommentUpdateResponse commentUpdateResponse) {
        commentService.update(memberId, commentId, commentUpdateResponse);
        return new ResponseEntity<>(commentUpdateResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> delete(@MemberId Long memberId,
                                    @PathVariable("commentId") Long commentId) {
        commentService.delete(memberId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
