package com.example.ERD_pj.Entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String grade;


}
