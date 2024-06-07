package com.example.ERD_pj.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

  private final SecretKey secretKey;

  // @Value : application.yml에서의 특정한 변수 데이터를 가져올 수 있음
  // string key는 jwt에서 사용 안하므로 객체 키 생성!
  // "${spring.jwt.secret}" : application.yml에 저장된 spring: jwt: secret 에 저장된 암호화 키 사용
  public JWTUtil(@Value("${spring.jwt.secret}")String secret) {
    this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
  }

  public String getCategory(String token) {
    return Jwts.parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .get("category", String.class);
  }
  // loginId 반환 메서드
  public String getEmail(String token) {
    return Jwts.parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .get("email", String.class);
  }

  // role 반환 메서드
  public String getRole(String token) {
    return Jwts.parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .get("role", String.class);
  }

  // 토큰이 소멸 (유효기간 만료) 하였는지 검증 메서드
  public Boolean isExpired(String token) {
    return Jwts.parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getExpiration()
        .before(new Date());
  }

  // 토큰 생성 메서드
  public String createJwt(String category, String email, String role, Long expiredMs) {
    return Jwts.builder()
        .claim("category", category)
        .claim("email", email)
        .claim("role", role)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expiredMs))
        .signWith(secretKey)
        .compact();
  }
}