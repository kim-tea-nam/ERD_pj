package com.example.ERD_pj.ApiController;

import com.example.ERD_pj.DTO.UserDTO;
import com.example.ERD_pj.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/getAll")
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    List<UserDTO> users = userService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<UserDTO> getID(@PathVariable("id") Long id) {
    UserDTO user = userService.getUserById(id);
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping("getBytoken")
  public ResponseEntity<UserDTO> getBytoken(HttpServletRequest request, HttpServletResponse response) {
    UserDTO user = userService.getBytoken(request, response);
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
    try {
      String response = userService.createUser(userDTO);
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    } catch (Exception e) {
      throw new RuntimeException("Error creating user", e);
    }
  }


  @PutMapping("/update/{id}")
  public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
    return userService.updateUser(id, userDTO);
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
    try {
      String response = userService.deleteUser(id);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException("Error deleting user", e);
    }
  }
}
