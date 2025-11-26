package com.arqui_web.tarifa_service.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	@Value("${jwt.secret}")
	private String secret;

	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}

	public List<String> extractUserRole(String token) {
		Claims claims = getClaims(token);
		String role = claims.get("role", String.class);
		return List.of(role);
	}

	public Claims getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
				.getBody();
	}

	public boolean isTokenValid(String token) {
		try {
			getClaims(token);
			return true;
		} catch (Exception e) {
			System.out.println("Token invalido: " + e.getMessage());
			return false;
		}
	}

}