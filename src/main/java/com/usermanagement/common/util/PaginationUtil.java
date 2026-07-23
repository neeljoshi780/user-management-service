package com.usermanagement.common.util;

import com.usermanagement.common.constants.PaginationConstants;
import com.usermanagement.common.request.FilterRequest;
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
	 * @param request  pagination and sorting request
	 * @return pageable object
	 */
	public static Pageable buildPageable(FilterRequest request) {
		if (request == null) {
			return getDefaultPageable();
		}
		int page = (request.getPageNumber() == null || request.getPageNumber() < 0)
			? PaginationConstants.DEFAULT_PAGE
			: request.getPageNumber();
		int size = (request.getPageSize() == null || request.getPageSize() <= 0)
			? PaginationConstants.DEFAULT_SIZE
			: Math.min(request.getPageSize(), PaginationConstants.MAX_PAGE_SIZE);
		String sortField = (request.getSortBy() == null || request.getSortBy().isBlank())
			? PaginationConstants.DEFAULT_SORT_BY
			: request.getSortBy();
		String direction = (request.getSortDir() == null || request.getSortDir().isBlank())
			? PaginationConstants.DEFAULT_SORT_DIR
			: request.getSortDir();
		Sort sort = direction.equalsIgnoreCase("desc")
			? Sort.by(sortField).descending()
			: Sort.by(sortField).ascending();
		return PageRequest.of(page, size, sort);
	}

	private static Pageable getDefaultPageable() {
		return PageRequest.of(
				PaginationConstants.DEFAULT_PAGE,
				PaginationConstants.DEFAULT_SIZE,
				Sort.by(PaginationConstants.DEFAULT_SORT_DIR, PaginationConstants.DEFAULT_SORT_BY)
		);
	}

}