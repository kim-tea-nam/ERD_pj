package com.example.ERD_pj.Service;

import com.example.ERD_pj.DTO.UserDTO;

import java.util.List;

public interface UserService {

  List<UserDTO> getAllUsers();
  UserDTO getUserById(Long id);
  String createUser(UserDTO userDTO);
  String updateUser(Long id, UserDTO userDTO);
  String deleteUser(Long id);
}
