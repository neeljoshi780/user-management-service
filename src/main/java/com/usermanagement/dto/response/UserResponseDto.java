package com.usermanagement.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for returning user data in API responses.
 * Excludes sensitive fields like password.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

	private Long id;

	private String username;

	private String email;

	private String firstName;

	private String lastName;

	private String mobile;

	private String gender;

	private LocalDate dateOfBirth;

}