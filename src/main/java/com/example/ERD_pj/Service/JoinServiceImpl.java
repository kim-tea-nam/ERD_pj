package com.example.ERD_pj.Service;

import com.example.ERD_pj.DTO.JoinDTO;
import com.example.ERD_pj.Entity.User;
import com.example.ERD_pj.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements JoinService{

  private final UserRepository userRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;


  public JoinServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }


  @Override
  public void JoinProcess(JoinDTO joinDTO) {
    String name = joinDTO.getName();
    String password = joinDTO.getPassword();

    Boolean isExist = userRepository.existsByname(joinDTO.getName());

    if (isExist) {

      return ;
    }

    User user = User.builder()
        .name(name)
        .password(bCryptPasswordEncoder.encode(password))
        .role("ROLE_ADMIN")
        .age(joinDTO.getAge())
        .email(joinDTO.getEmail())
        .build();

    userRepository.save(user);

  }
}
