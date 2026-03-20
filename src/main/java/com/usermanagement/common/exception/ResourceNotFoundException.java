package com.usermanagement.common.exception;

/**
 * Thrown when requested resource is not found.
 */
public class ResourceNotFoundException extends BaseException {

	public ResourceNotFoundException(String message) {
		super(message);
	}

}