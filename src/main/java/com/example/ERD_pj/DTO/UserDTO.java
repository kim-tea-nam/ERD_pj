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

  public static UserDTO fromEntity(User user) {
    return UserDTO.builder()
        .name(user.getName())
        .age(user.getAge())
        .build();
  }

  public User toEntity() {
    return User.builder()
        .name(this.name)
        .age(this.age)
        .build();
  }

}
