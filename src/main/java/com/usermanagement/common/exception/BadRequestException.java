package com.usermanagement.common.exception;

/**
 * Thrown when client sends invalid or malformed request.
 */
public class BadRequestException extends BaseException {

	public BadRequestException(String message) {
		super(message);
	}

}