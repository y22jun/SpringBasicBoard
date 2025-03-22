package org.zeorck.likelionboard.common.response;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.Builder;

@Builder
public record PageableResponse<T>(
        int page,
        int size,
        int totalPages,
        int totalElements,
        boolean isEnd,
        List<T> content
) {
    public static <T> PageableResponse<T> of(Pageable pageable, List<T> totalElements) {
        int totalPageSize = (int)Math.ceil((double)totalElements.size() / pageable.getPageSize());
        boolean isEnd = pageable.getPageNumber() + 1 >= totalPageSize;
        return PageableResponse.<T>builder()
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .totalPages(totalPageSize)
                .totalElements(totalElements.size())
                .isEnd(isEnd)
                .content(totalElements)
                .build();
    }
}
