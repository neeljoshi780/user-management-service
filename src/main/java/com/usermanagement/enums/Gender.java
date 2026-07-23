package com.usermanagement.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.usermanagement.common.exception.BadRequestException;

/**
 * Enum representing user gender.
 */
public enum Gender {

	MALE,
	FEMALE,
	OTHER;

	/**
	 * Creates a Gender enum from a case-insensitive value.
	 *
	 * @param value gender value
	 * @return matching Gender enum
	 * @throws IllegalArgumentException if value is invalid
	 */
	@JsonCreator
	public static Gender fromValue(String value) {
		if (value == null || value.isBlank()) {
			return null;
		}
		for (Gender gender : values()) {
			if (gender.name().equalsIgnoreCase(value)) {
				return gender;
			}
		}
		throw new BadRequestException(
				"Invalid gender. Allowed values are: MALE, FEMALE, OTHER."
		);
	}

}