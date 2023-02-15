package com.fmartinez.disney.app.config;

import com.fmartinez.disney.app.security.JwtAuthenticationFilter;
import com.fmartinez.disney.app.security.handler.AppAccessDeniedHandler;
import com.fmartinez.disney.app.security.handler.AppAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService service;
    private final JwtAuthenticationFilter jwtFilter;
    private final AppAuthenticationEntryPoint entryPoint;
    private final AppAccessDeniedHandler deniedHandler;
    private final String[] patterns = {"/api/characters/**", "/api/genre/**", "/api/characters/**"};
    private final String[] dataBaseDoc = {"/", "/h2", "/h2/**", "/openapi/**"};


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(dataBaseDoc)
                .permitAll()
                .requestMatchers("/api/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(provider(passwordEncoder(), service))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .exceptionHandling().accessDeniedHandler(deniedHandler);

        http.headers().frameOptions().disable();

        return http.build();
    }

    @Bean
    public AuthenticationProvider provider(PasswordEncoder password, UserDetailsService service) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(service);
        authProvider.setPasswordEncoder(password);
        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
