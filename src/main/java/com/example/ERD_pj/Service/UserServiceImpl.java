package com.example.ERD_pj.Service;

import com.example.ERD_pj.DTO.UserDTO;
import com.example.ERD_pj.Entity.User;
import com.example.ERD_pj.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
    if (user != null) {
      userRepository.save(user);
      return "유저 생성 성공";
    }
    return "유저 생성 실패";
  }

  @Override
  public String updateUser(Long id, UserDTO userDTO) {
    Optional<User> optionalUser = userRepository.findById(id);
    if(optionalUser.isEmpty()){
      return "해당하는 ID에 유저 데이터가 없습니다.";
    }
    User user = new User(userDTO.getId(), userDTO.getName(), userDTO.getAge(), userDTO.getEmail());
    userRepository.save(user);
    return "해당하는 ID에 유저 데이터를 업데이트 했습니다.";
  }

  @Override
  public String deleteUser(Long id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if(optionalUser.isEmpty()){
      return "해당하는 ID에 유저 데이터가 없습니다.";
    }

    userRepository.deleteById(id);
    return "해당하는 ID에 유저 데이터를 삭제 했습니다.";
  }
}
