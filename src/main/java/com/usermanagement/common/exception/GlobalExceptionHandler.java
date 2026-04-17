package com.usermanagement.common.exception;

import com.usermanagement.common.constants.MessageConstants;
import com.usermanagement.common.response.ApiResponse;
import com.usermanagement.common.response.ErrorResponseDto;
import com.usermanagement.common.util.ApiResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles all application exceptions and returns
 * standardized API responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Common method to build error response.
	 */
	private ResponseEntity<ApiResponse<Object>> buildErrorResponse(HttpStatus status, HttpServletRequest request, String message, Map<String, String> fieldErrors) {
		ErrorResponseDto error = ErrorResponseDto.builder()
			.error(status.name())
			.message(message)
			.path(request.getRequestURI())
			.fieldErrors(fieldErrors)
			.build();
		return ResponseEntity.status(status).body(ApiResponseBuilder.error(status.value(), message, error));
	}

	/**
	 * Validation errors (@Valid)
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
		Map<String, String> fieldErrors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
			.forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
		return buildErrorResponse(HttpStatus.BAD_REQUEST, request, MessageConstants.VALIDATION_FAILED, fieldErrors);
	}

	/**
	 * Duplicate resource (e.g. email exists)
	 */
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ApiResponse<Object>> handleDuplicate(DuplicateResourceException ex, HttpServletRequest request) {
		Map<String, String> fieldErrors = null;
		if (ex.getFieldName() != null) {
			fieldErrors = new HashMap<>();
			fieldErrors.put(ex.getFieldName(), ex.getMessage());
		}
		return buildErrorResponse(HttpStatus.CONFLICT, request, ex.getMessage(), fieldErrors);
	}

	/**
	 * Resource not found
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
		return buildErrorResponse(HttpStatus.NOT_FOUND, request, ex.getMessage(), null);
	}

	/**
	 * Bad request
	 */
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiResponse<Object>> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
		return buildErrorResponse(HttpStatus.BAD_REQUEST, request, ex.getMessage(), null);
	}

	/**
	 * Business rule violation
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse<Object>> handleBusiness(BusinessException ex, HttpServletRequest request) {
		return buildErrorResponse(HttpStatus.CONFLICT, request, ex.getMessage(),null);
	}

	/**
	 * Fallback (unexpected errors)
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleGlobal(Exception ex, HttpServletRequest request) {
		return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, request, MessageConstants.INTERNAL_SERVER_ERROR,null);
	}

}