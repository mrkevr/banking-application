package dev.mrkevr.bankingapplication.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.mrkevr.bankingapplication.dto.UserInfoResponse;
import dev.mrkevr.bankingapplication.entity.User;

@Component
public class UserMapper {
	
	public UserInfoResponse toUserInfoResponse(User user) {
		UserInfoResponse userInfoResponse = UserInfoResponse.builder()
			.fullName(user.getFullName())
			.address(user.getAddress())
			.phone(user.getPhone())
			.email(user.getEmail())
			.accountNumber(user.getAccountNumber())
			.accountBalance(user.getAccountBalance())
			.isActive(user.isActive())
			.build();
		
		return userInfoResponse;
	}
	
	public List<UserInfoResponse> toUserInfoResponse(List<User> users) {
		return users.stream().map(user -> this.toUserInfoResponse(user)).collect(Collectors.toList());
	}
}
