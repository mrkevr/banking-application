package dev.mrkevr.bankingapplication.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.mrkevr.bankingapplication.config.jwt.JwtService;
import dev.mrkevr.bankingapplication.dto.GenericResponse;
import dev.mrkevr.bankingapplication.dto.TokenRequest;
import lombok.RequiredArgsConstructor;

@RequestMapping("/authentication")
@RestController
@RequiredArgsConstructor
public class JwtController {

	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/token")
	public ResponseEntity<GenericResponse> getToken(@RequestBody TokenRequest tokenRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(tokenRequest.getEmail(), tokenRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			String token = jwtService.generateToken(tokenRequest.getEmail());
			GenericResponse genericResponse = GenericResponse.builder()
					.status(HttpStatus.OK.name())
					.apiCode("")
					.timeStamp(LocalDateTime.now())
					.message("Request token success")
					.body(token)
					.build();
			return ResponseEntity.ok(genericResponse);
		} else {
			throw new UsernameNotFoundException("Invalid credentials");
		}
	}
}
