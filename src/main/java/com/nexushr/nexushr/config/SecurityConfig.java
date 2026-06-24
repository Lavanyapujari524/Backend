package com.nexushr.nexushr.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.client.RestTemplate;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nexushr.nexushr.security.JwtFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {

        this.jwtFilter = jwtFilter;
    }

    // CORS CONFIGURATION
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of(
                        "https://frontend-l3h3.vercel.app/",
                        "http://localhost:5173"
                )
        );

        configuration.setAllowedMethods(
                List.of("*")
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }

    // SECURITY FILTER CHAIN
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http

            .csrf(csrf -> csrf.disable())

            .cors(cors -> {})

            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS
                    )
            )

            .authorizeHttpRequests(auth -> auth

                // PUBLIC APIs
            		.requestMatchers(
            		        "/auth/**",
            		        "/setup/**",
            		        "/employees/**",
            		        "/attendance/**",
            		        "/leave/**",
            		        "/dashboard/**",
            		        "/export/**",
            		        "/payslip/**",
            		        "/swagger-ui/**",
            		        "/swagger-ui.html",
            		        "/v3/api-docs/**",
            		        "/ws/**",
            		        "/candidates/**",
            		        "/excel/**",
            		        "/notify/**"
            		).permitAll()

                // EMPLOYEE APIs
                .requestMatchers("/employees/**")
                .permitAll()

                // PAYROLL APIs
                .requestMatchers("/payroll/**")
                .hasAnyRole("ADMIN", "HR")

                // LEAVE APPROVAL
                .requestMatchers("/leave/approve/**")
                .hasRole("MANAGER")

                // ATTENDANCE
                .requestMatchers("/attendance/**")
                .permitAll()

                .requestMatchers("/export/**")
                .permitAll()
                .requestMatchers("/leave/**")
                .permitAll()
                .requestMatchers(
                	    "/employees/upload-photo"
                	).permitAll()
                // ALL OTHER APIs
                .anyRequest()
                .authenticated()
            )

            .addFilterBefore(
                    jwtFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    // REST TEMPLATE
    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

    // PASSWORD ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}