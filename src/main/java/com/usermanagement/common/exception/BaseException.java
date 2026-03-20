package com.usermanagement.common.exception;

/**
 * Parent class for all custom exceptions.
 * Helps standardize exception handling across application.
 */
public class BaseException extends RuntimeException {

	public BaseException(String message) {
		super(message);
	}

}