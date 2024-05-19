package com.example.ERD_pj.DTO;

import com.example.ERD_pj.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private Long id;
  private String name;
  private Integer age;
  private String email;

  // User를 UserDTO로 변환
  public static UserDTO fromEntity(User user) {
    return UserDTO.builder()
        .name(user.getName())
        .age(user.getAge())
        .email(user.getEmail())
        .build();
  }

  // UserDTO를 User로 변환
  public User toEntity() {
    return User.builder()
        .name(this.name)
        .age(this.age)
        .email(this.email)
        .build();
  }

}