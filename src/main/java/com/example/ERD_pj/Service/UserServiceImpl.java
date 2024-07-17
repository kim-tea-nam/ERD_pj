package com.example.ERD_pj.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.ERD_pj.DTO.UserDTO;
import com.example.ERD_pj.Entity.User;
import com.example.ERD_pj.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;


  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public List<UserDTO> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(UserDTO::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public UserDTO getUserById(Long id) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      return UserDTO.fromEntity(user);
    } else {
      return null;
    }
  }

  @Override
  public UserDTO getUserByemail(String email) {
    User user = userRepository.findByemail(email);
    if (user != null) {
      return UserDTO.fromEntity(user);
    } else {
      return null;
    }
  }


  @Override
  public String createUser(UserDTO userDTO) {
    User user = userDTO.toEntity();
    userRepository.save(user);
    return "User created successfully";
  }

  @Override
  public ResponseEntity<String> updateUser(Long id, UserDTO userDTO) {
    return userRepository.findById(id)
        .map(userToUpdate -> {
          userToUpdate.updateDetails(userDTO.getName(), userDTO.getAge(), userDTO.getEmail());
          userRepository.save(userToUpdate);
          return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND));
  }

  @Override
  public String deleteUser(Long id) {
    if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
      return "User deleted successfully";
    } else {
      return "User not found";
    }
  }

  @Override
  public UserDTO getBytoken(HttpServletRequest request, HttpServletResponse response) {
    String access = request.getHeader("access");

    String secretKey = "vmfhaltmskdlstkfkdgodyroqkfwkdbalroqkfwkdbalaaaaaaaaaaaaaaaabbbbb";

    try {
      // 토큰 디코딩
      DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
          .build()
          .verify(access);

      // 이메일 추출
      String email = decodedJWT.getClaim("email").asString();

      System.out.println("이메일: " + email);

      User user = userRepository.findByemail(email);

      return UserDTO.fromEntity(user);

    } catch (JWTDecodeException exception) {
      // 유효하지 않은 토큰
      System.out.println("유효하지 않은 토큰입니다.");
    }

    return null;
  }

}
