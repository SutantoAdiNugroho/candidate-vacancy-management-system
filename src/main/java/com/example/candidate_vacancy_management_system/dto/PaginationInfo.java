package com.example.candidate_vacancy_management_system.dto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    public static <T> PaginationInfo createPagination(Pageable pageable, List<T> allDatas) {
        int totalPages = (int) Math.ceil((double) allDatas.size() / pageable.getPageSize());
        return PaginationInfo.builder()
                .page(pageable.getPageNumber())
                .perPage(pageable.getPageSize())
                .totalItem(allDatas.size())
                .totalPage(totalPages)
                .build();
    }
}
