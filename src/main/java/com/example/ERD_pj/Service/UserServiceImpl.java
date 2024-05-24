package com.example.ERD_pj.Service;

import com.example.ERD_pj.DTO.UserDTO;
import com.example.ERD_pj.Entity.User;
import com.example.ERD_pj.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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

}
