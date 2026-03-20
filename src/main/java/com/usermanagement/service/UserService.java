package com.usermanagement.service;

import com.usermanagement.common.request.UserSearchRequestDto;
import com.usermanagement.common.response.PageResponseDto;
import com.usermanagement.dto.request.CreateUserRequestDto;
import com.usermanagement.dto.request.UpdateUserRequestDto;
import com.usermanagement.dto.response.UserResponseDto;

/**
 * Defines business operations for User management.
 * Acts as a contract between controller and implementation.
 */
public interface UserService {

	/**
	 * Creates a new user.
	 *
	 * @param request user request data containing user details
	 * @return created user response DTO
	 */
	UserResponseDto createUser(CreateUserRequestDto request);

	/**
	 * Retrieves a user by its unique identifier.
	 *
	 * @param id user ID
	 * @return user response DTO
	 */
	UserResponseDto getUserById(Long id);

	/**
	 * Retrieves paginated users with optional search and sorting.
	 *
	 * @param request pagination, sorting, and search parameters
	 * @return paginated user list
	 */
	PageResponseDto<UserResponseDto> getUsers(UserSearchRequestDto request);

	/**
	 * Updates an existing user.
	 *
	 * @param id user ID
	 * @param request updated user data
	 * @return updated user response DTO
	 */
	UserResponseDto updateUser(Long id, UpdateUserRequestDto request);

	/**
	 * Deletes a user by ID.
	 *
	 * @param id user ID
	 */
	void deleteUser(Long id);

}