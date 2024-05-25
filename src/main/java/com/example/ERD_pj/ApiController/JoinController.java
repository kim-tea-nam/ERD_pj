package com.example.ERD_pj.ApiController;


import com.example.ERD_pj.DTO.JoinDTO;
import com.example.ERD_pj.Service.JoinService;
import lombok.Getter;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JoinController {

  private final JoinService joinService;


  public JoinController(JoinService joinService) {
    this.joinService = joinService;
  }


  @PostMapping("/signUp")
  public String joinProcess(@RequestBody JoinDTO joinDTO) {
    joinService.JoinProcess(joinDTO);
    return "ok";
  }

//  @PostMapping("/login")
//  public void loginProcess(){
//
//  }
//
//  @GetMapping("/userGet")
//  public  void userGet() {
//
//  }


}
