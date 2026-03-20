package com.usermanagement.common.exception;

import lombok.Getter;

/**
 * Thrown when uniqueness constraint is violated.
 * Example: duplicate email, username.
 */
@Getter
public class DuplicateResourceException extends BaseException {

	private final String fieldName;

	public DuplicateResourceException(String message) {
		super(message);
		this.fieldName = null;
	}

	public DuplicateResourceException(String message, String fieldName) {
		super(message);
		this.fieldName = fieldName;
	}

}