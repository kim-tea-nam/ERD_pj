package com.example.ERD_pj.JWT;

import com.example.ERD_pj.DTO.CustomUserDetails;
import com.example.ERD_pj.Entity.User;
import com.example.ERD_pj.Service.UserService;
import com.example.ERD_pj.Service.UserServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

public class JWTFilter extends OncePerRequestFilter {

  private final JWTUtil jwtUtil;
  private final UserService userService;

  public JWTFilter(JWTUtil jwtUtil, UserService userService) {
    this.jwtUtil = jwtUtil;
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // 헤더에서 access키에 담긴 토큰을 꺼냄
    String accessToken = request.getHeader("access");

    // 토큰이 없다면 다음 필터로 넘김
    if (accessToken == null) {
      filterChain.doFilter(request, response);
      return;
    }

    // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
    try {
      jwtUtil.isExpired(accessToken);
    } catch (ExpiredJwtException e) {

      //response body
      PrintWriter writer = response.getWriter();
      writer.print("access token expired");

      //response status code
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
      return;
    }

    // 토큰이 access인지 확인 (발급시 페이로드에 명시)
    String category = jwtUtil.getCategory(accessToken);

    if (!category.equals("access")) {

      //response body
      PrintWriter writer = response.getWriter();
      writer.print("invalid access token");

      //response status code
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401 상태코드
      return;
    }
    String email = jwtUtil.getEmail(accessToken);
    System.out.println(email);

    User user = userService.getUserByemail(email).toEntity();

    CustomUserDetails customUserDetails = new CustomUserDetails(user);

    Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authToken);

    filterChain.doFilter(request, response);
  }

}

