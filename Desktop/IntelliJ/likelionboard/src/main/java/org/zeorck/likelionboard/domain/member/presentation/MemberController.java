package org.zeorck.likelionboard.domain.member.presentation;

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
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MemberSaveResponse memberSaveResponse) {
        memberService.save(memberSaveResponse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> update(@MemberId Long memberId, @RequestBody MemberNicknameUpdateResponse memberNicknameUpdateResponse) {
        memberService.updateNickname(memberId, memberNicknameUpdateResponse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
