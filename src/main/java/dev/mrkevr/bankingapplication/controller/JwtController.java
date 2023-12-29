package dev.mrkevr.bankingapplication.controller;

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
import dev.mrkevr.bankingapplication.dto.TokenRequest;
import lombok.RequiredArgsConstructor;

@RequestMapping("/authentication")
@RestController
@RequiredArgsConstructor
public class JwtController {
	
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	@PostMapping("/token")
	public ResponseEntity<?> getToken(@RequestBody TokenRequest tokenRequest) {
		
		System.out.println("JWT CONTROLLER");
		
		 Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(tokenRequest.getEmail(), tokenRequest.getPassword()));
		 
		 if (authentication.isAuthenticated()) {
	             String token = jwtService.generateToken(tokenRequest.getEmail());
	             return ResponseEntity.ok(token);
	        } else {
	            throw new UsernameNotFoundException("Invalid credentials");
	        }
	}

}
