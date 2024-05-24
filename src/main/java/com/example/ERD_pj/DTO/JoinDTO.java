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
public class JoinDTO {

  private String name;
  private String password;
  private String role;

  private Integer age;
  private String email;

}
