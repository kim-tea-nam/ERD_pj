package com.example.ERD_pj.Repository;

import com.example.ERD_pj.DTO.UserDTO;
import com.example.ERD_pj.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Boolean existsByname(String name);

  User findByemail(String email);

}
