package com.example.ERD_pj.Entity;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String grade;


}
