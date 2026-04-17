package com.usermanagement.common.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Standard error response DTO.
 *
 * Used for validation, business, and system errors.
 * Provides structured error details for client applications.
 */
@Getter
@Builder
public class ErrorResponseDto {

	private String error;

	private String message;

	private String path;

	private Map<String, String> fieldErrors;

}