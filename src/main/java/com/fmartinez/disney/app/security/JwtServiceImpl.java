package com.fmartinez.disney.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.fmartinez.disney.app.constant.ApplicationConstant.*;

@Service
public class JwtServiceImpl implements JwtService {

    @Override
    public String createToken(UserDetails userDetails) {
        return createToken(new HashMap<>(), userDetails);
    }

    @Override
    public String createToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKE_EXPIRATION))
                .signWith(SECRET_KEY)
                .compact();
    }

    @Override
    public String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsFunction) {
        final Claims claims = getClaims(token);
        return claimsFunction.apply(claims);
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(processToken(token))
                .getBody();
    }

    @Override
    public String processToken(String token) {
        return StringUtils.remove(token, TOKEN_PREFIX);
    }

    private Boolean isTokenExpired(String token) {
        Date test = extractClaim(token, Claims::getExpiration);
        return test.before(new Date());
    }
}
