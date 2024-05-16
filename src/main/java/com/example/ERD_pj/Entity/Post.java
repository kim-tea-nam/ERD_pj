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
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @ManyToOne
  @JoinColumn(name = "user_id",nullable = false)
  private User writer; // 유저 엔티티를 참조하는 필드

}