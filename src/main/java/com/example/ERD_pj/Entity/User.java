package com.example.ERD_pj.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor  // 모든 필드를 인자로 받는 생성자 추가
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String role;

  @Column(nullable = false)
  private Integer age;

  @Column(nullable = false, unique = true)
  private String email;

  public void updateDetails(String name, Integer age, String email) {
    if (name != null) {
      this.name = name;
    }
    if (age != null) {
      this.age = age;
    }
    if (email != null) {
      this.email = email;
    }
  }



}
