package com.example.ERD_pj.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

  private Long id;
  private String title;
  private String content;
  private Long writerId;
  private String writerName; // Post 엔티티에 User를 참조하여 이름을 가져옴

}
