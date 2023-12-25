package dev.mrkevr.bankingapplication.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.mrkevr.bankingapplication.dto.UserCreationRequest;
import dev.mrkevr.bankingapplication.dto.UserInfoResponse;
import dev.mrkevr.bankingapplication.entity.User;
import dev.mrkevr.bankingapplication.exception.UserNotFoundException;
import dev.mrkevr.bankingapplication.mapper.UserMapper;
import dev.mrkevr.bankingapplication.repository.UserRepository;
import dev.mrkevr.bankingapplication.util.UserUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	@Override
	public UserInfoResponse create(UserCreationRequest userCreationRequest) {
		
		/*
		 * Generate a 10-digit account number
		 */
		String accountNumber = UserUtils.generateAccountNumber();
		while (userRepository.existsByAccountNumber(accountNumber)) {
			accountNumber = UserUtils.generateAccountNumber();
		}
		
		/*
		 * Email availability check
		 */
		if (userRepository.existsByEmail(userCreationRequest.getEmail())) {
			throw new RuntimeException("Email is already taken.");
		}
		
		/*
		 * Create a user from request
		 */
		User newUser = User.builder()
			.fullName(userCreationRequest.getFullName())
			.address(userCreationRequest.getAddress())
			.phone(userCreationRequest.getPhone())
			.email(userCreationRequest.getEmail())
			.accountNumber(UserUtils.generateAccountNumber())
			.accountBalance(BigDecimal.ZERO)
			.isActive(true)
			.build();
		
		/*
		 * Persist user
		 */
		User savedUser = userRepository.save(newUser);
		
		return userMapper.toUserInfoResponse(savedUser);
	}
	
	@Override
	public List<UserInfoResponse> getAll(int page, int limit) {
		PageRequest pageRequest = PageRequest.of(page, limit);
		List<User> users = userRepository.findAll(pageRequest).getContent();
		return userMapper.toUserInfoResponse(users);
	}

	@Override
	public UserInfoResponse getById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return userMapper.toUserInfoResponse(user);
	}

}
