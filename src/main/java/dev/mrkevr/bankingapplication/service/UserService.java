package dev.mrkevr.bankingapplication.service;

import java.util.List;

import dev.mrkevr.bankingapplication.dto.ChangePasswordRequest;
import dev.mrkevr.bankingapplication.dto.UserCreationRequest;
import dev.mrkevr.bankingapplication.dto.UserInfoResponse;

public interface UserService {

	UserInfoResponse create(UserCreationRequest userCreationRequest);

	List<UserInfoResponse> getAll(int page, int limit);

	UserInfoResponse getById(Long id);

	boolean isValidUser(String email, String password);

	boolean changePassword(ChangePasswordRequest changePasswordRequest);
	
	void deleteById(Long id);
}
