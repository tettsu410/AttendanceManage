package com.example.AttendanceManage;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.swing.*;

// SpringBootでのセキュリティ定義を行うクラスです。
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final MyUserDetailsService userDetailsService;

    public SecurityConfig(MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // ログインに関する定義
        http.formLogin(login -> login
                // ログイン処理を行うURL
                .loginProcessingUrl("/login")
                // ログインページのURL
                .loginPage("/login")
                //.successHandler(new MyAuthenticationSuccessHandler()) // カスタムの成功ハンドラを指定
                //ログイン成功時に表示するページ
                .defaultSuccessUrl("/attendanceList")
                .failureUrl("/login?error")
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/")
        ).authorizeHttpRequests(authz -> authz
                //認証や許可に関する定義

                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/").permitAll()
                // general以下はUSERの役割が必要
                //.requestMatchers("/general").hasRole("USER")
                // admin以下はADMINの役割が必要
                //.requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()

        ).csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
