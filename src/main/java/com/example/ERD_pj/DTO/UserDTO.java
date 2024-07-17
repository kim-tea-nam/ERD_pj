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
  private String password;
  private String role;
  private Integer age;
  private String email;


  // User를 UserDTO로 변환
  public static UserDTO fromEntity(User user) {
    return UserDTO.builder()
        .id(user.getId())
        .name(user.getName())
        .age(user.getAge())
        .email(user.getEmail())
        .build();
  }


  // UserDTO를 User로 변환
  public User toEntity() {
    return User.builder()
        .id(this.id)
        .name(this.name)
        .age(this.age)
        .email(this.email)
        .build();
  }

  public User join(UserDTO userDTO) {
    return User.builder()
        .name(userDTO.getName())
        .email(userDTO.getEmail())
        .build();
  }

    public void Setemail(String email){
      this.email = email;
    }
}
