package com.usermanagement.common.constants;

/**
 * API path constants used across controllers.
 * Ensures consistency and easy version management.
 */
public final class ApiPaths {

	// Prevent instantiation
	private ApiPaths() {}

	public static final String BASE = "/api";
	public static final String VERSION_V1 = "/v1";
	public static final String API_V1 = BASE + VERSION_V1;

	// COMMON PATH VARIABLES
	public static final String ID = "/{id}";

	// USER MODULE
	public static final String USERS = API_V1 + "/users";

}