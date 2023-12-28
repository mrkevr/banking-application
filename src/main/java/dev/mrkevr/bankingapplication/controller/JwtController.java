package dev.mrkevr.bankingapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.mrkevr.bankingapplication.config.jwt.JwtService;
import dev.mrkevr.bankingapplication.dto.TokenRequest;
import lombok.RequiredArgsConstructor;

@RequestMapping("/authentication")
@RestController
@RequiredArgsConstructor
public class JwtController {
	
	private final JwtService jwtService;
	
	@GetMapping("/token")
	public ResponseEntity<?> getToken(@RequestBody TokenRequest tokenRequest) {
		return null;
	}

}
