package org.zeorck.likelionboard.domain.saveboard.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zeorck.likelionboard.common.annotation.MemberId;
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
}
