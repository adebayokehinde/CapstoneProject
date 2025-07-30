package com.example.truckstorm.config;


import com.example.truckstorm.util.JwtServiceUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    @NotBlank
    private final JwtServiceUtil jwtServiceUtil;

    private UserDetails userDetails;

    private String username;


    @Override
    protected void doFilterInternal(
                @NonNull HttpServletRequest request,
                @NonNull HttpServletResponse response,
                @NonNull FilterChain filterChain)

            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        assert authHeader != null;
        final String jwtToken = authHeader.substring(7).trim();
        username = jwtServiceUtil.extractEmail(jwtToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userDetailsService.loadUserByUsername(username);
        }

        if(jwtServiceUtil.isTokenValid(jwtToken, userDetails)){
            var authToken = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
//    public void setAuthenticationContent(String token){
//        String userId = jwtServiceUtil.extractUserId(token);
//        String userType = jwtServiceUtil.extractUserType(token);
//
//        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + userType));
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                userId,
//                null,
//                authorities
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
}
