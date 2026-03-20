package com.usermanagement.common.constants;

/**
 * Common response messages.
 */
public final class MessageConstants {

	private MessageConstants() {}

	// GENERAL
	public static final String INTERNAL_SERVER_ERROR = "Internal server error";

	// VALIDATION
	public static final String VALIDATION_FAILED = "Validation failed";
	public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
	public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
	public static final String PHONE_ALREADY_EXISTS = "Mobile number already exists";

	// USER MODULE
	public static final String USER_CREATED = "User created successfully";
	public static final String USER_UPDATED = "User updated successfully";
	public static final String USER_DELETED = "User deleted successfully";
	public static final String USER_FETCHED = "User fetched successfully";
	public static final String USERS_FETCHED = "Users fetched successfully";
	public static final String USER_NOT_FOUND = "User not found";

}