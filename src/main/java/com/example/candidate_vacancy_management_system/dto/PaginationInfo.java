package com.example.candidate_vacancy_management_system.dto;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationInfo {
    private int page;
    private int perPage;
    private long totalItem;
    private int totalPage;

    public static <T> PaginationInfo createPagination(Page<T> pageResult) {
        int page = pageResult.getNumber();
        return PaginationInfo.builder()
                .page(page)
                .perPage(pageResult.getSize())
                .totalItem(pageResult.getTotalElements())
                .totalPage(pageResult.getTotalPages())
                .build();
    }
}
