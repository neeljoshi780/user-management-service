package com.usermanagement.common.request;

import lombok.*;

/**
 * Encapsulates pagination, sorting, and search parameters.
 * Used for fetching paginated data.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSearchRequestDto {

	// pagination
	private Integer pageNumber;
	private Integer pageSize;
	private String sortBy;
	private String sortDir;

	// filters
	private String search;

}