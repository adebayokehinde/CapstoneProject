package com.example.truckstorm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

//    public SecurityWebFilterChain springSecurityFilterChain(HttpSecurity http) throws Exception {
//        HttpSecurity httpSecurity = http.authorizeHttpRequests(
//                authorizeRequests -> authorizeRequests
//                        .requestMatchers()
//                        .requestMatchers("api/load/create")
//                        .hasAnyRole("USER", "ADMIN")
//                        .anyRequest()
//                        .authenticated()
//
//
//        );
//        return httpSecurity;
//    }
}
