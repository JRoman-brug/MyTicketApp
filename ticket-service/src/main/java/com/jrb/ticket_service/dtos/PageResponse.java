package com.jrb.ticket_service.dtos;

import java.util.List;

import org.springframework.data.domain.Page;

public record PageResponse<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        String nextPath,
        String prevPath) {
    public PageResponse(Page<T> page, String baseUrl) {
        this(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext() ? baseUrl + "?page=" + (page.getNumber() + 1) + "&size=" + page.getSize() : null,
                page.hasPrevious() ? baseUrl + "?page=" + (page.getNumber() - 1) + "&size=" + page.getSize() : null);
    }
}
