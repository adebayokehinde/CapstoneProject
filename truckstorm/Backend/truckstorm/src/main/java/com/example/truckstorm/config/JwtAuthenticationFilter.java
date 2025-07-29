package com.example.truckstorm.config;

import com.example.truckstorm.util.JwtServiceUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtServiceUtil jwtService;
    private JwtServiceUtil jwtServiceUtil;

    @Override
    protected void doFilterInternal(
                @NonNull HttpServletRequest request,
                @NonNull HttpServletResponse response,
                @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken ;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7);
        if(jwtServiceUtil.isTokenValid(jwtToken)){
            setAuthenticationContent(jwtToken);
        }

    }
    public void setAuthenticationContent(String token){
        String userId = jwtServiceUtil.extractUserId(token);
        String userType = jwtServiceUtil.extractUserType(token).name();

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER_TYPE" + userType));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userId,
                null,
                authorities
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
