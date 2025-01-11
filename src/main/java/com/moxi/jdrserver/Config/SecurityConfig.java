package com.moxi.jdrserver.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .ignoringRequestMatchers("/api/**")
                .and()
                .authorizeRequests()
                .requestMatchers("/api/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/api/auth/login") // Specify the URL of your custom login page
                .loginProcessingUrl("/process-login")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/api/auth/logout")
                .invalidateHttpSession(true);
        return http.build();
    };
}
