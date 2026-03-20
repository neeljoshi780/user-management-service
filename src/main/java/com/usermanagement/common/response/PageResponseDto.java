package com.usermanagement.common.response;

import lombok.*;

import java.util.List;

/**
 * Generic pagination response DTO.
 *
 * Used to return paginated data along with
 * pagination metadata.
 *
 * @param <T> type of elements in the page
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponseDto<T> {

	private List<T> content;

	private int pageNumber;

	private int pageSize;

	private long totalElements;

	private int totalPages;

	private boolean hasNext;

	private boolean hasPrevious;

}