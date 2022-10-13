package com.haruhanjan.authentication.config.security;

import com.haruhanjan.authentication.service.oauth2.OAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuthUserService oAuthUserService;
    private final OAuth2SuccessHandler successHandler;
    private final CorsConfig corsConfig;
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .oauth2Login()
                    .authorizationEndpoint()
                    .baseUri("/api/oauth2")
                    .and()
                    .successHandler(successHandler)
                    .userInfoEndpoint()
                    .userService(oAuthUserService)
                    .and()
                .and()
                .cors().configurationSource(corsConfig.corsConfigurationSource())
                .and()
                .csrf().disable()
                .build();
    }

}
