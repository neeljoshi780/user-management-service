package com.usermanagement.common.util;

import com.usermanagement.common.constants.PaginationConstants;
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
	 * @param pageNumber   page number (0-based)
	 * @param pageSize page size
	 * @param sortBy   sorting field
	 * @param sortDir  sorting direction (asc/desc)
	 * @return pageable object
	 */
	public static Pageable buildPageable(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		int page = (pageNumber == null || pageNumber < 0)
			? PaginationConstants.DEFAULT_PAGE
			: pageNumber;
		int size = (pageSize == null || pageSize <= 0)
			? PaginationConstants.DEFAULT_SIZE
			: Math.min(pageSize, PaginationConstants.MAX_PAGE_SIZE);
		String sortField = (sortBy == null || sortBy.isBlank())
			? PaginationConstants.DEFAULT_SORT_BY
			: sortBy;
		String direction = (sortDir == null || sortDir.isBlank())
			? PaginationConstants.DEFAULT_SORT_DIR
			: sortDir;
		Sort sort = direction.equalsIgnoreCase("desc")
			? Sort.by(sortField).descending()
			: Sort.by(sortField).ascending();
		return PageRequest.of(page, size, sort);
	}

}