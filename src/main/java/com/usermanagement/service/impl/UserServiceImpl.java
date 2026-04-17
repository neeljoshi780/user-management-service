package com.usermanagement.service.impl;

import com.usermanagement.common.constants.MessageConstants;
import com.usermanagement.common.exception.DuplicateResourceException;
import com.usermanagement.common.exception.ResourceNotFoundException;
import com.usermanagement.common.request.UserSearchRequestDto;
import com.usermanagement.common.response.PageResponseDto;
import com.usermanagement.common.util.PaginationUtil;
import com.usermanagement.dto.request.CreateUserRequestDto;
import com.usermanagement.dto.request.UpdateUserRequestDto;
import com.usermanagement.dto.response.UserResponseDto;
import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;
import com.usermanagement.service.UserService;
import com.usermanagement.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of UserService.
 * Handles business logic, validation, pagination, and mapping.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final ModelMapper modelMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserResponseDto createUser(CreateUserRequestDto request) {
		validateDuplicate(request);
		User user = modelMapper.map(request, User.class);
		User savedUser = userRepository.save(user);
		return toResponseDto(savedUser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserResponseDto getUserById(Long id) {
		return toResponseDto(findUserById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PageResponseDto<UserResponseDto> getUsers(UserSearchRequestDto request) {
		Pageable pageable = PaginationUtil.buildPageable(request);
		Page<User> userPage = userRepository.findAll(UserSpecification.globalSearch(request.getSearch()), pageable);
		List<UserResponseDto> content = userPage.getContent()
			.stream()
			.map(this::toResponseDto)
			.toList();
		return buildPageResponse(userPage, content);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserResponseDto updateUser(Long id, UpdateUserRequestDto request) {
		User user = findUserById(id);
		validateDuplicateForUpdate(request, user);
		modelMapper.map(request, user);
		User updatedUser = userRepository.save(user);
		return toResponseDto(updatedUser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteUser(Long id) {
		User user = findUserById(id);
		userRepository.delete(user);
	}

	/**
	 * Finds user by ID or throws exception.
	 */
	private User findUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.USER_NOT_FOUND));
	}

	/**
	 * Validates duplicate fields for create operation.
	 */
	private void validateDuplicate(CreateUserRequestDto request) {
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new DuplicateResourceException(MessageConstants.EMAIL_ALREADY_EXISTS, "email");
		}
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new DuplicateResourceException(MessageConstants.USERNAME_ALREADY_EXISTS, "username");
		}
		if (userRepository.existsByMobile(request.getMobile())) {
			throw new DuplicateResourceException(MessageConstants.PHONE_ALREADY_EXISTS, "mobile");
		}
	}

	/**
	 * Validates duplicate fields for update operation.
	 */
	private void validateDuplicateForUpdate(UpdateUserRequestDto request, User existingUser) {
		if (!existingUser.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
			throw new DuplicateResourceException(MessageConstants.EMAIL_ALREADY_EXISTS, "email");
		}
		if (!existingUser.getUsername().equals(request.getUsername()) && userRepository.existsByUsername(request.getUsername())) {
			throw new DuplicateResourceException(MessageConstants.USERNAME_ALREADY_EXISTS, "username");
		}
		if (!existingUser.getMobile().equals(request.getMobile()) && userRepository.existsByMobile(request.getMobile())) {
			throw new DuplicateResourceException(MessageConstants.PHONE_ALREADY_EXISTS, "mobile");
		}
	}

	/**
	 * Converts User entity to response DTO.
	 */
	private UserResponseDto toResponseDto(User user) {
		return modelMapper.map(user, UserResponseDto.class);
	}

	/**
	 * Builds paginated response DTO.
	 */
	private PageResponseDto<UserResponseDto> buildPageResponse(Page<User> page, List<UserResponseDto> content) {
		return PageResponseDto.<UserResponseDto>builder()
			.content(content)
			.pageNumber(page.getNumber())
			.pageSize(page.getSize())
			.totalElements(page.getTotalElements())
			.totalPages(page.getTotalPages())
			.hasNext(page.hasNext())
			.hasPrevious(page.hasPrevious())
			.build();
	}

}