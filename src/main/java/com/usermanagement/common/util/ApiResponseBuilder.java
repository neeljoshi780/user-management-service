package com.usermanagement.common.util;

import com.usermanagement.common.response.ApiResponse;
import com.usermanagement.common.response.ErrorResponseDto;

import java.time.LocalDateTime;

/**
 * Utility class for building ApiResponse objects.
 *
 * Provides static methods to create standardized success and error responses.
 * Ensures consistent API response structure across the application.
 *
 * This class centralizes response creation logic and keeps ApiResponse
 * as a pure data holder.
 *
 * Supports:
 * - Success responses with data
 * - Error responses with error details
 *
 * Note: This is a utility class and should not be instantiated.
 */
public class ApiResponseBuilder {

	/**
	 * Builds a success response.
	 *
	 * @param status HTTP status code
	 * @param message success message
	 * @param data actual response data
	 * @return ApiResponse object with success=true
	 */
	public static <T> ApiResponse<T> success(int status, String message, T data) {
		return ApiResponse.<T>builder()
				.success(true)
				.status(status)
				.message(message)
				.data(data)
				.timestamp(LocalDateTime.now())
				.build();
	}

	/**
	 * Builds an error response.
	 *
	 * @param status HTTP status code
	 * @param message error message
	 * @param error error details
	 * @return ApiResponse object with success=false
	 */
	public static <T> ApiResponse<T> error(int status, String message, ErrorResponseDto error) {
		return ApiResponse.<T>builder()
				.success(false)
				.status(status)
				.message(message)
				.error(error)
				.timestamp(LocalDateTime.now())
				.build();
	}

}