package com.arqui_web.tarifa_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	private static final String[] SWAGGER_WHITELIST = 
		{ 
			"/swagger-ui/**", 
			"/swagger-ui.html", 
			"/v3/api-docs/**",
			"/v3/api-docs", 
			"/api-docs", 
			"/api-docs/**" 
		};

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(SWAGGER_WHITELIST).permitAll() // Swagger libre
						.anyRequest().authenticated());

		return http.build();
	}
}
