package com.usermanagement.common.util;

import com.usermanagement.common.constants.PaginationConstants;
import com.usermanagement.common.request.UserSearchRequestDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Utility class for building Pageable objects with
 * safe defaults and validations.
 */
public final class PaginationUtil {

	private PaginationUtil() {}

	/**
	 * Builds pageable configuration with safe defaults.
	 *
	 * @param requestDto  pagination and sorting request
	 * @return pageable object
	 */
	public static Pageable buildPageable(UserSearchRequestDto requestDto) {
		int page = (requestDto.getPageNumber() == null || requestDto.getPageNumber() < 0)
			? PaginationConstants.DEFAULT_PAGE
			: requestDto.getPageNumber();
		int size = (requestDto.getPageSize() == null || requestDto.getPageSize() <= 0)
			? PaginationConstants.DEFAULT_SIZE
			: Math.min(requestDto.getPageSize(), PaginationConstants.MAX_PAGE_SIZE);
		String sortField = (requestDto.getSortBy() == null || requestDto.getSortBy().isBlank())
			? PaginationConstants.DEFAULT_SORT_BY
			: requestDto.getSortBy();
		String direction = (requestDto.getSortDir() == null || requestDto.getSortDir().isBlank())
			? PaginationConstants.DEFAULT_SORT_DIR
			: requestDto.getSortDir();
		Sort sort = direction.equalsIgnoreCase("desc")
			? Sort.by(sortField).descending()
			: Sort.by(sortField).ascending();
		return PageRequest.of(page, size, sort);
	}

}