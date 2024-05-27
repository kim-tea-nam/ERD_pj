package com.example.ERD_pj.config;

import com.example.ERD_pj.JWT.JWTFilter;
import com.example.ERD_pj.JWT.JWTUtil;
import com.example.ERD_pj.JWT.LoginFilter;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
  private final AuthenticationConfiguration authenticationConfiguration;
  private final JWTUtil jwtUtil;

  @Autowired
  public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
    this.authenticationConfiguration = authenticationConfiguration;
    this.jwtUtil = jwtUtil;
  }

  //AuthenticationManager Bean 등록
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

    return configuration.getAuthenticationManager();
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {

    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf((auth) -> auth.disable());
    http.formLogin((auth) -> auth.disable());
    http.httpBasic((auth) -> auth.disable());
    http.authorizeHttpRequests((auth) -> auth
            .requestMatchers("/join/**","/login").permitAll()
            .requestMatchers("/reissue").permitAll()
            .requestMatchers("/user").hasRole("ADMIN")
            .anyRequest().authenticated());

    http.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
    http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);


    http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
  }
}