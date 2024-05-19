package com.example.ERD_pj.ApiController;

import com.example.ERD_pj.DTO.UserDTO;
import com.example.ERD_pj.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/getAll")
  public List<UserDTO> getAllusers() {
    return userService.getAllUsers();
  }

  @GetMapping("/getID/{id}")
  public UserDTO getID(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @PostMapping("/create")
  public String CreateUser(@RequestBody UserDTO userDTO) {
    return userService.createUser(userDTO);
  }

  @PutMapping("/update/{id}")
  public String UpdateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
    return userService.updateUser(id, userDTO);
  }

  @DeleteMapping("/delete/{id}")
  public String deleteUser(@PathVariable Long id) {
    return userService.deleteUser(id);
  }


}
