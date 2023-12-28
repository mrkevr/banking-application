package dev.mrkevr.bankingapplication.config.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import dev.mrkevr.bankingapplication.dto.TokenRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {

	@Value("${application-security.jwt.secret}")
	private String jwtSecret;

	@Value("${application-security.jwt.expiration}")
	private long jwtExpiration;

	public String generateToken(Authentication authentication) {

		String subject = authentication.getName();
		Date currentDateTime = new Date();
		Date expiratedDateTime = new Date(currentDateTime.getTime() + jwtExpiration);

		String token = Jwts.builder().setSubject(subject).setIssuedAt(currentDateTime).setExpiration(expiratedDateTime)
				.signWith(getKey()).compact();

		return token;
	}
	
	
	
	public String getTokenFromRequest(HttpServletRequest httpServletRequest) {
		String bearerToken = httpServletRequest.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	/*
	 * Returns the JWT sub (subject) value or null if not present
	 */
	public String getSubject(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getKey()).build().parse(token);
			
			return true;
		} catch (MalformedJwtException malformedJwtException) {
			throw new JwtException(malformedJwtException.getMessage());
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new JwtException(illegalArgumentException.getMessage());
		} catch (SignatureException signatureException) {
			throw new JwtException(signatureException.getMessage());
		}
	}

	private Key getKey() {
		byte[] bytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(bytes);
	}

}
