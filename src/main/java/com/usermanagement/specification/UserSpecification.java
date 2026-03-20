package com.usermanagement.specification;

import com.usermanagement.model.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides dynamic search and filtering logic
 * for User entity using JPA Specifications.
 *
 * Supports global search across multiple
 * User fields.
 */
public final class UserSpecification {

	// Prevent instantiation
	private UserSpecification() {}

	/**
	 * Creates global search specification.
	 *
	 * @param search search keyword
	 * @return specification predicate
	 */
	public static Specification<User> globalSearch(String search) {
		return (root, query, cb) -> {
			// Return all records if search is null or blank
			if (search == null || search.isBlank()) {
				return cb.conjunction();
			}

			String likePattern = "%" + search.toLowerCase() + "%";
			List<Predicate> predicates = new ArrayList<>();

			if (search.matches("\\d+")) {
				predicates.add(cb.equal(root.get("id"), Long.valueOf(search)));
			}
			predicates.add(cb.like(cb.lower(root.get("username")), likePattern));
			predicates.add(cb.like(cb.lower(root.get("email")), likePattern));
			predicates.add(cb.like(cb.lower(root.get("firstName")), likePattern));
			predicates.add(cb.like(cb.lower(root.get("lastName")), likePattern));
			predicates.add(cb.like(cb.lower(root.get("mobile")), likePattern));
			predicates.add(cb.like(cb.lower(root.get("gender").as(String.class)), likePattern));
			predicates.add(cb.like(cb.lower(root.get("status").as(String.class)), likePattern));
			predicates.add(cb.like(root.get("dateOfBirth").as(String.class), likePattern));
			// Combine all conditions using OR
			return cb.or(predicates.toArray(new Predicate[0]));
		};
	}

}