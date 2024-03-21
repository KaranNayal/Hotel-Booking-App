package com.hexaware.CozyHeavenStay.security;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.hexaware.CozyHeavenStay.customexception.BadRequestException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtTokenProvider {
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	@Value("${app-jwt-expiration-milliseconds}")
	private int jwtExpirationDate;
	// Generate JWT token
	public String generateToken(Authentication authentication)
	{
		String username = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		/*
		 * List<String> roles = authorities.stream()
		 * .map(GrantedAuthority::getAuthority) .collect(Collectors.toList());
		 */
		String roles = authorities.stream()
		        .map(GrantedAuthority::getAuthority)
		        .collect(Collectors.joining(","));
		String jwtToken = Jwts.builder()
								.setSubject(username)
								.claim("role", roles)
								.setIssuedAt(new Date())
								.setExpiration(expireDate)
								.signWith(key())
								.compact();
		return jwtToken;
	}
	private Key key()
	{
		return Keys.hmacShaKeyFor(
					Decoders.BASE64.decode(jwtSecret)
				);
	}
	// get username from token
	public String getUsername(String token)
	{
		Claims claims = Jwts.parserBuilder()
							.setSigningKey(key()).build()
							.parseClaimsJws(token)
							.getBody();
		String username = claims.getSubject();
		return username;
	}
	// validate token
	public boolean validateToken(String token)
	{
		try {
				Jwts.parserBuilder()
				.setSigningKey(key())
				.build()
				.parse(token);
				return true;
		} 
		catch (MalformedJwtException e) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
		}
		catch(ExpiredJwtException e) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Expired JWT Token");
		}
		catch(UnsupportedJwtException e) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Unsupported JWT Token");
		}
		catch(IllegalArgumentException e) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
		}
	}
}