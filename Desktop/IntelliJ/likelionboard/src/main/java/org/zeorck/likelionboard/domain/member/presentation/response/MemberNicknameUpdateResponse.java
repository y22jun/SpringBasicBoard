package org.zeorck.likelionboard.domain.member.presentation.response;

import lombok.Builder;

@Builder
public record MemberNicknameUpdateResponse(
        Long memberId
) {
}
