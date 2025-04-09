package org.zeorck.likelionboard.domain.comment.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeorck.likelionboard.common.annotation.MemberId;
import org.zeorck.likelionboard.domain.comment.application.CommentService;
import org.zeorck.likelionboard.domain.comment.presentation.request.CommentSaveRequest;
import org.zeorck.likelionboard.domain.comment.presentation.request.CommentUpdateRequest;
import org.zeorck.likelionboard.domain.comment.presentation.response.CommentInfoResponse;
import org.zeorck.likelionboard.domain.comment.presentation.response.CommentSaveResponse;
import org.zeorck.likelionboard.domain.comment.presentation.response.CommentUpdateResponse;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Tag(name = "댓글", description = "댓글 관리 API")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 저장", description = "특정 게시글 안에 댓글을 저장합니다.")
    @ApiResponse(responseCode = "201")
    @PostMapping("/{boardId}")
    public ResponseEntity<CommentSaveResponse> save(
            @MemberId Long memberId,
            @PathVariable("boardId") Long boardId,
            @Valid @RequestBody CommentSaveRequest commentSaveRequest
    ) {
        CommentSaveResponse commentSaveResponse = commentService.save(memberId, boardId, commentSaveRequest);
        return new ResponseEntity<>(commentSaveResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "댓글 수정", description = "특정 게시글 안에 있는 댓글을 수정합니다.")
    @ApiResponse(responseCode = "200")
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentUpdateResponse> update(
            @MemberId Long memberId,
            @PathVariable("commentId") Long commentId,
            @Valid @RequestBody CommentUpdateRequest commentUpdateRequest
    ) {
        CommentUpdateResponse commentUpdateResponse = commentService.update(memberId, commentId, commentUpdateRequest);
        return new ResponseEntity<>(commentUpdateResponse, HttpStatus.OK);
    }

    @Operation(summary = "댓글 삭제", description = "특정 게시글 안에 있는 댓글을 삭제합니다.")
    @ApiResponse(responseCode = "204")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> delete(
            @MemberId Long memberId,
            @PathVariable("commentId") Long commentId
    ) {
        commentService.delete(memberId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "댓글 조회", description = "특정 게시글 안에 있는 댓글을 모두 조회합니다.")
    @ApiResponse(responseCode = "200")
    @GetMapping("/{boardId}")
    public ResponseEntity<List<CommentInfoResponse>> getCommentsByBoardId(
            @PathVariable("boardId") Long boardId
    ) {
        List<CommentInfoResponse> comments = commentService.getCommentsByBoardId(boardId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
