package com.haruhanjan.authentication.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated() // 그 외 요청은 권한 필요
                .and()
                /*
                .logout()
                .logoutUrl("/api/auth/logout")
                .deleteCookies("JSESSIONID","access_token","refresh_token")
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        // 세션 무효화
                        HttpSession session = request.getSession();
                        session.invalidate();

                        // Security Context 삭제
                        SecurityContextHolder.clearContext();
                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setStatus(204);
                    }
                })
                .and()
                 */
                .formLogin().disable()
                .cors().disable()
                .csrf().disable()
                .httpBasic().disable()
                .headers().disable();
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .and()
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // 매 요청 전 JwtFilter 적용
        /*
        쿠키 설정은 컨트롤러에서 했고 필터는 쿠키에
         */
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
