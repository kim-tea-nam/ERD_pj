package com.example.ERD_pj.Service;

import com.example.ERD_pj.DTO.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

  List<UserDTO> getAllUsers();
  UserDTO getUserById(Long id);
  String createUser(UserDTO userDTO);
  ResponseEntity<String> updateUser(Long id, UserDTO userDTO);
  String deleteUser(Long id);
}
