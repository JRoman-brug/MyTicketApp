package com.jrb.ticket_service.dtos;

import java.util.List;

import org.springframework.data.domain.Page;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Generic pagination response wrapper.
 * Provides a standardized structure for paginated API responses with navigation
 * links.
 * 
 * @param <T>           the type of elements in the page content
 * @param content       list of items in the current page
 * @param pageNumber    current page number (zero-based)
 * @param pageSize      number of items per page
 * @param totalElements total number of items across all pages
 * @param totalPages    total number of pages
 * @param nextPath      URL path to the next page, or null if this is the last
 *                      page
 * @param prevPath      URL path to the previous page, or null if this is the
 *                      first page
 */
@Schema(name = "PageResponse", description = "Generic pagination response wrapper")
public record PageResponse<T>(
        @Schema(description = "List of items in the current page") List<T> content,
        @Schema(description = "Current page number (zero-based)", example = "0") int pageNumber,
        @Schema(description = "Number of items per page", example = "10") int pageSize,
        @Schema(description = "Total number of items across all pages", example = "100") long totalElements,
        @Schema(description = "Total number of pages", example = "10") int totalPages,
        @Schema(description = "URL path to the next page", example = "/api/movies?page=1&size=10", nullable = true) String nextPath,
        @Schema(description = "URL path to the previous page", example = "/api/movies?page=0&size=10", nullable = true) String prevPath) {

    /**
     * Convenience constructor that converts a Spring Data Page object into a
     * PageResponse.
     * Automatically generates next and previous navigation links.
     * 
     * @param page    the Spring Data Page to convert
     * @param baseUrl base URL for generating navigation links
     */
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
