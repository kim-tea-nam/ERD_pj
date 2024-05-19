package com.example.ERD_pj.Service;

import com.example.ERD_pj.DTO.UserDTO;

import java.util.List;

public interface UserService {

  List<UserDTO> getAllUsers();
  UserDTO getUserById(Long id);
  String createUser(UserDTO userDTO);
  UserDTO updateUser(Long id, UserDTO userDTO);
  void deleteUser(Long id);
}
