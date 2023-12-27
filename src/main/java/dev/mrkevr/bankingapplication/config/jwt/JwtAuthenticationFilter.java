package dev.mrkevr.bankingapplication.config.jwt;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	JwtTokenService jwtTokenService;
	UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
		/*
		 * Extract token from the request
		 */
		Optional<String> optionalToken = jwtTokenService.getTokenFromRequest(request);
		if(!optionalToken.isPresent()) {
			throw new JwtException("Token not found");
		}
		
		/*
		 * Validate the token
		 */
		String token = optionalToken.get();
		if(!jwtTokenService.validateToken(token)) {
			throw new JwtException("Token not valid");
		}
		
		/*
		 * Fetch the user(email) from the token and check it from the database
		 */
		String username = jwtTokenService.getSubject(token);
		userDetailsService.loadUserByUsername(username);
		
		
	}
}
