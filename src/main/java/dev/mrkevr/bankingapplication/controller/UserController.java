package dev.mrkevr.bankingapplication.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.mrkevr.bankingapplication.dto.ChangePasswordRequest;
import dev.mrkevr.bankingapplication.dto.GenericResponse;
import dev.mrkevr.bankingapplication.dto.UserCreationRequest;
import dev.mrkevr.bankingapplication.dto.UserInfoResponse;
import dev.mrkevr.bankingapplication.service.UserService;
import dev.mrkevr.bankingapplication.util.UserUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

	UserService userService;
	
	/*
	 * Save one user
	 */
	@PostMapping
	ResponseEntity<GenericResponse> saveUser(@RequestBody UserCreationRequest userCreationRequest) {
		
		UserInfoResponse userInfoResponse = userService.create(userCreationRequest);
		
		GenericResponse genericResponse = GenericResponse.builder()
			.status(HttpStatus.CREATED.name())
			.apiCode(UserUtils.USER_CREATION_SUCCESS_CODE)
			.timeStamp(LocalDateTime.now())
			.message(UserUtils.USER_CREATION_SUCCESS_MESSAGE)
			.body(userInfoResponse)
			.build();
		
		return ResponseEntity.created(URI.create("")).body(genericResponse);
	}
	
	/*
	 * Get users
	 */
	@GetMapping
	ResponseEntity<GenericResponse> getUsers(
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "100") int limit) {
		
		List<UserInfoResponse> users = userService.getAll(page, limit);
		
		GenericResponse genericResponse = GenericResponse.builder()
			.status(HttpStatus.OK.name())
			.apiCode(UserUtils.USER_FIND_SUCCESS_CODE)
			.timeStamp(LocalDateTime.now())
			.message("")
			.body(users)
			.build();
		
		return ResponseEntity.ok(genericResponse);
	}
	
	/*
	 * Return one user by id
	 */
	@GetMapping("/{id}")
	ResponseEntity<GenericResponse> getUserById(@PathVariable Long id) {

		UserInfoResponse userInfoResponse = userService.getById(id);

		GenericResponse genericResponse = GenericResponse.builder()
				.status(HttpStatus.OK.name())
				.apiCode(UserUtils.USER_FIND_SUCCESS_CODE)
				.timeStamp(LocalDateTime.now())
				.message("")
				.body(userInfoResponse)
				.build();

		return ResponseEntity.ok(genericResponse);
	}
	
	@PatchMapping("/change-password")
	ResponseEntity<GenericResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {

		boolean changePasswordSuccessful = userService.changePassword(changePasswordRequest);
		
		String status;
		String apiCode;
		if (changePasswordSuccessful) {
			status = HttpStatus.OK.name();
			apiCode = UserUtils.PASSWORD_CHANGE_SUCCESS_CODE;
		} else {
			status = HttpStatus.UNPROCESSABLE_ENTITY.name();
			apiCode = UserUtils.PASSWORD_CHANGE_FAILED_CODE;
		}
		
		GenericResponse genericResponse = GenericResponse.builder()
				.status(status)
				.apiCode(apiCode)
				.timeStamp(LocalDateTime.now())
				.message("Password change " + (changePasswordSuccessful ? "success" : "failed"))
				.body("")
				.build();

		return changePasswordSuccessful ? ResponseEntity.ok(genericResponse) : ResponseEntity.unprocessableEntity().body(genericResponse);
	}
	
	
	
	
	
	
}
