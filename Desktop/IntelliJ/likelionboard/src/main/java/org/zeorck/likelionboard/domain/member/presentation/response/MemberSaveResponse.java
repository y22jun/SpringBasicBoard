package org.zeorck.likelionboard.domain.member.presentation.response;

//이메일 검증 Validator 쓰자.
public record MemberSaveResponse(String email, String password, String nickname) {
}
