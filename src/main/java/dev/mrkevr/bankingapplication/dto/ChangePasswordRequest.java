package dev.mrkevr.bankingapplication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

	@NotBlank(message = "email must not be blank`")
	private String email;

	@NotBlank(message = "currentPassword must not be blank")
	private String currentPassword;

	@NotBlank(message = "newPassword must not be blank")
	private String newPassword;
}
