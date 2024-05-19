package com.example.ERD_pj.ApiController;

import com.example.ERD_pj.DTO.UserDTO;
import com.example.ERD_pj.Entity.User;
import com.example.ERD_pj.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/User")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/getAll")
  @ResponseBody
  public List<UserDTO> getAlluser() {
    return userService.getAllUsers();
  }

  @GetMapping("/getID")
  @ResponseBody
  public String ww() {
    return "Hello World";
  }


}
