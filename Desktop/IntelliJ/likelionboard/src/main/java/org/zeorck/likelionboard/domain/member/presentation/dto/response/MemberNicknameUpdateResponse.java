package org.zeorck.likelionboard.domain.member.presentation.dto.response;

import lombok.Builder;

@Builder
public record MemberNicknameUpdateResponse(
        Long memberId
) {
}
