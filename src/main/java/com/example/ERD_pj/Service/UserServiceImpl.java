package com.example.ERD_pj.Service;

import com.example.ERD_pj.DTO.UserDTO;
import com.example.ERD_pj.Entity.User;
import com.example.ERD_pj.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream()
        .map(UserDTO::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public UserDTO getUserById(Long id) {
    // ID에 해당하는 사용자를 데이터베이스에서 가져옴
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

    // 가져온 사용자를 UserDTO로 변환하여 반환
    return UserDTO.fromEntity(user);
  }

  @Override
  public String createUser(UserDTO userDTO) {
    User user = User.builder()
        .name(userDTO.getName())
        .age(userDTO.getAge())
        .email(userDTO.getEmail())
        .build();
    if (user == null) {
      return "실패";
    }
    userRepository.save(user);
    return "성공";
  }

  @Override
  public UserDTO updateUser(Long id, UserDTO userDTO) {
    return null;
  }

  @Override
  public void deleteUser(Long id) {

  }
}
