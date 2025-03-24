package org.zeorck.likelionboard.domain.member.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeorck.likelionboard.common.annotation.MemberId;
import org.zeorck.likelionboard.domain.member.application.MemberService;
import org.zeorck.likelionboard.domain.member.presentation.response.MemberNicknameUpdateResponse;
import org.zeorck.likelionboard.domain.member.presentation.response.MemberSaveResponse;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Tag(name = "멤버", description = "멤버 관리 API")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "회원가입을 합니다.")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody MemberSaveResponse memberSaveResponse) {
        memberService.save(memberSaveResponse);
        return new ResponseEntity<>(memberSaveResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "멤버 닉네임 수정", description = "멤버 닉네임을 수정합니다.")
    @ApiResponse(responseCode = "200")
    @PutMapping
    public ResponseEntity<?> update(
            @MemberId Long memberId,
            @RequestBody MemberNicknameUpdateResponse memberNicknameUpdateResponse) {
        memberService.updateNickname(memberId, memberNicknameUpdateResponse);
        return new ResponseEntity<>(memberNicknameUpdateResponse, HttpStatus.OK);
    }
}
