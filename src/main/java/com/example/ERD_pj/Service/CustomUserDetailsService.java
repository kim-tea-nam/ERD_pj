package com.example.ERD_pj.Service;

import com.example.ERD_pj.DTO.CustomUserDetails;
import com.example.ERD_pj.Entity.User;
import com.example.ERD_pj.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findByname(username);

    if (user != null) {

      //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
      return new CustomUserDetails(user);
    }
    return null;
  }
}
