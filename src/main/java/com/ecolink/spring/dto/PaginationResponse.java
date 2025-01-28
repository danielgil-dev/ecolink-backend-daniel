package com.ecolink.spring.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaginationResponse<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean isLast;

    public PaginationResponse(List<T> content, int page, int size, long totalElements, int totalPages, boolean isLast) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.isLast = isLast;
    }
}
