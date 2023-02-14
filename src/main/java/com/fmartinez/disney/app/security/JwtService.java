package com.fmartinez.disney.app.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    String createToken(UserDetails userDetails);

    String createToken(Map<String, Object> claims, UserDetails userDetails);

    Boolean isTokenValid(String token, UserDetails userDetails);

    Claims getClaims(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsFunction);

    String processToken(String token);

    String getUsername(String token);
}
