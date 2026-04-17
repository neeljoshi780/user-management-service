package com.usermanagement.common.response;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Standard API response wrapper.
 *
 * Used for all API responses (success + error).
 * Ensures consistent structure across application.
 *
 * @param <T> type of response data
 */
@Getter
@Builder
public class ApiResponse<T> {

	private final boolean success;

	private final int status;

	private final String message;

	private final T data;

	private final ErrorResponseDto error;

	private final LocalDateTime timestamp;

}