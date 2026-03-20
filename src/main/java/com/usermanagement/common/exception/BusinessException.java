package com.usermanagement.common.exception;

/**
 * Thrown when business rules are violated.
 * Example: account blocked, invalid state.
 */
public class BusinessException extends BaseException {

	public BusinessException(String message) {
		super(message);
	}

}