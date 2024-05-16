package com.example.ERD_pj.ApiController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

  @GetMapping("/")
  @ResponseBody
  public String main() {
    return "Hello World";
  }
}
