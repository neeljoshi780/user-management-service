package com.usermanagement.repository;

import com.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entity.
 * Provides CRUD operations and custom query methods.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

	/**
	 * Check if email exists.
	 */
	boolean existsByEmail(String email);

	/**
	 * Check if username exists.
	 */
	boolean existsByUsername(String username);

	/**
	 * Check if mobile exists.
	 */
	boolean existsByMobile(String mobile);

}