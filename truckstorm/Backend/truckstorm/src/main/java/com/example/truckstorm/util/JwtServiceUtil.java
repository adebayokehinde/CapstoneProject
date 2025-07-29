package com.example.truckstorm.util;

import com.example.truckstorm.data.models.UserType;
import com.example.truckstorm.dtos.request.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceUtil {
    public final SecretKey SECRET_KEY;
    private static final String ISSUER = "locallink";
    private final long EXPIRATION_TIME;

    public JwtServiceUtil(
            @Value("${jwt.secret}") SecretKey base64SecretKey,
            @Value("${jwt.expiration}") long expiration) {
        this.SECRET_KEY = base64SecretKey;
        this.EXPIRATION_TIME = expiration;
    }

    public String generateToken( UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userDetails.getEmail());
        claims.put("firstname", userDetails.getFirstname());
        claims.put("userType", userDetails.getUserType().name());
        return createToken(userDetails.getId(), claims);
    }

    public String createToken( String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(subject)
                .setClaims(claims)
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try{
            parseToken(token);
            return !isTokenExpired(token);
        }catch(Exception e){
            return false;
        }
    }
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractFirstName(String token) {
        return extractClaim(token, claims -> claims.get("firstname", String.class));
    }

    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = parseToken(token);
        return claimsResolver.apply(claims);
    }

}
