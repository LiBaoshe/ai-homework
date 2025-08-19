package com.ibaoge.rbac_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// SecurityConfig.java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/public/**").permitAll() // 公开接口
                        .anyRequest().authenticated() // 其他所有接口都需要认证
                )
                // 我们的细粒度权限由@RequiresPermission注解和AOP控制，
                // 所以这里只做基本的认证和URL层级的大致控制
                .formLogin(withDefaults()) // 或者使用.httpBasic()，或JWT等
                .csrf(csrf -> csrf.disable()); // 根据实际情况决定是否禁用CSRF

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}