package com.usermanagement.controller;

import com.usermanagement.common.constants.ApiPaths;
import com.usermanagement.common.constants.MessageConstants;
import com.usermanagement.common.request.UserSearchRequestDto;
import com.usermanagement.common.response.ApiResponse;
import com.usermanagement.common.response.PageResponseDto;
import com.usermanagement.dto.request.CreateUserRequestDto;
import com.usermanagement.dto.request.UpdateUserRequestDto;
import com.usermanagement.dto.response.UserResponseDto;
import com.usermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * User REST APIs.
 * Handles CRUD operations with pagination and search.
 */
@RestController
@RequestMapping(ApiPaths.USERS)
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	/**
	 * Creates a new user.
	 *
	 * @param request user request payload
	 * @return created user response wrapped in ApiResponse
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@Valid @RequestBody CreateUserRequestDto request) {
		UserResponseDto response = userService.createUser(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response, MessageConstants.USER_CREATED));
	}

	/**
	 * Retrieves a user by ID.
	 *
	 * @param id user identifier
	 * @return user response wrapped in ApiResponse
	 */
	@GetMapping(ApiPaths.ID)
	public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(@PathVariable Long id) {
		UserResponseDto response = userService.getUserById(id);
		return ResponseEntity.ok(ApiResponse.success(response, MessageConstants.USER_FETCHED));
	}

	/**
	 * Retrieves paginated users with optional search and sorting.
	 *
	 * @param request pagination, sorting, and search parameters
	 * @return paginated user list
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<PageResponseDto<UserResponseDto>>> getUsers(@ModelAttribute UserSearchRequestDto request) {
		PageResponseDto<UserResponseDto> response = userService.getUsers(request);
		return ResponseEntity.ok(ApiResponse.success(response, MessageConstants.USERS_FETCHED));
	}

	/**
	 * Updates an existing user.
	 *
	 * @param id user identifier
	 * @param request updated user data
	 * @return updated user response
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequestDto request) {
		UserResponseDto response = userService.updateUser(id, request);
		return ResponseEntity.ok(ApiResponse.success(response, MessageConstants.USER_UPDATED));
	}

	/**
	 * Deletes a user by ID.
	 *
	 * @param id user identifier
	 * @return success response with no data
	 */
	@DeleteMapping(ApiPaths.ID)
	public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok(ApiResponse.success(null, MessageConstants.USER_DELETED));
	}

}