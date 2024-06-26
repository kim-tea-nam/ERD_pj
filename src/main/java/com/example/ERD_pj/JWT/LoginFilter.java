package com.example.ERD_pj.JWT;

import com.example.ERD_pj.Entity.User;
import com.example.ERD_pj.Repository.UserRepository;
import com.example.ERD_pj.Service.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final JWTUtil jwtUtil;
  private final UserRepository userRepository;

  public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserRepository userRepository) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
    this.userRepository = userRepository;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

    //클라이언트 요청에서 username, password 추출
    String email = obtainUsername(request);
    String password = obtainPassword(request);

    System.out.println(email);
    System.out.println(password);

    //스프링 시큐리티에서 email과 password를 검증하기 위해서는 token에 담아야 함
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, null);

    //token에 담은 검증을 위한 AuthenticationManager로 전달
    return authenticationManager.authenticate(authToken);
  }

  //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
    String username = authentication.getName();


    // 사용자의 정보를 데이터베이스에서 가져옵니다.
    User user = userRepository.findByname(username);

    // 사용자의 이메일을 가져옵니다.
    String email = user.getEmail();

    System.out.println(email);

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
    GrantedAuthority auth = iterator.next();
    String role = auth.getAuthority();


    //토큰 생성
    String access = jwtUtil.createJwt("access", email, role, 600000L);
    String refresh = jwtUtil.createJwt("refresh", email, role, 86400000L);

    //응답 설정
    response.setHeader("access", access);
    response.setHeader("refresh", refresh);
    response.setStatus(HttpStatus.OK.value());
  }

  //로그인 실패시 실행하는 메소드
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
    response.setStatus(401);
  }

}


