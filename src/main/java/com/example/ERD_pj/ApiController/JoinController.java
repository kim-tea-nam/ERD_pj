package com.example.ERD_pj.ApiController;


import com.example.ERD_pj.DTO.JoinDTO;
import com.example.ERD_pj.Service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/join")
public class JoinController {

  private final JoinService joinService;

  @Autowired
  public JoinController(JoinService joinService) {
    this.joinService = joinService;
  }


  @PostMapping("/signUp")
  public String joinProcess(@RequestBody JoinDTO joinDTO) {
    joinService.JoinProcess(joinDTO);
    return "ok";
  }
  
}
