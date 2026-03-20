package com.usermanagement.dto.request;

import com.usermanagement.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO for updating user information.
 * Contains validation rules for user input.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {

	@NotBlank(message = "Username is required")
	@Size(min = 2, max = 30, message = "Username must be between 2 and 30 characters")
	@Pattern(regexp = "^[a-zA-Z0-9._]+$", message = "Username can contain letters, numbers, dot and underscore only")
	private String username;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Size(max = 254, message = "Email must not exceed 254 characters")
	private String email;

	@NotBlank(message = "First name is required")
	@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
	private String firstName;

	@Size(max = 50, message = "Last name must not exceed 50 characters")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Last name must contain only letters")
	private String lastName;

	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Enter a valid 10-digit mobile number")
	private String mobile;

	@NotNull(message = "Gender is required")
	private Gender gender;

	@NotNull(message = "Date of birth is required")
	@Past(message = "Date of birth must be a past date")
	private LocalDate dateOfBirth;

}