package com.example.truckstorm.util;

import com.example.truckstorm.data.models.CustomUserDetails;
import com.example.truckstorm.data.models.UserType;
import io.jsonwebtoken.JwtException;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


@Component
public class JwtServiceUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;


    public boolean hasClaim(Claims claimName, String key) {
        final Claims claim = extractAllClaims(key);
        return claim.get(claimName) != null;
    }

    private Claims extractAllClaims(String key) {
        return parseToken(key);
    }

    private SecretKey getSignedKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }


    public String generateToken( UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userDetails instanceof CustomUserDetails ? ((CustomUserDetails) userDetails).getUsername() : userDetails.getUsername());
        return createToken(claims, userDetails);
    }

    public String createToken(Map<String, Object> claims , UserDetails userDetails) {
        return Jwts
                .builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignedKey())
                .compact();
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String sanitizedToken = sanitizeToken(token);
            final String username = extractUsername(sanitizedToken);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(sanitizedToken);
        } catch (IllegalArgumentException | JwtException e) {
            return false; // Return false for invalid tokens
        }
    }

    private String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public Claims parseToken(String token) {
        String sanitizedToken = sanitizeToken(token);
        try {
            return Jwts.parser()
                    .verifyWith(getSignedKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new IllegalArgumentException("Invalid JWT token: " + e.getMessage());
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (IllegalArgumentException | JwtException e) {
            return true;
        }
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUserType(String token) {
        String userType = extractClaim(token, claims -> claims.get("userType", String.class));
        return userType != null ? userType : "";
    }

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractFirstName(String token) {
        return extractClaim(token, claims -> claims.get("firstname", String.class));
    }

    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email",  String.class));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = parseToken(token);
            return claimsResolver.apply(claims);
        } catch (IllegalArgumentException | JwtException e) {
            return null;
        }
    }

    private String sanitizeToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("JWT token cannot be null or empty");
        }
        String sanitized = token.trim();
        if (sanitized.contains(" ")) {
            throw new IllegalArgumentException("JWT token contains invalid whitespace characters");
        }
        // Basic format check: should contain two dots (header.payload.signature)
        if (sanitized.chars().filter(ch -> ch == '.').count() != 2) {
            throw new IllegalArgumentException("JWT token does not match expected format (header.payload.signature)");
        }
        return sanitized;
    }

}
