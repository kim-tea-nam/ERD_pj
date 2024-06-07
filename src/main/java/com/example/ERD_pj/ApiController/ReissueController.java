package com.example.ERD_pj.ApiController;

import com.example.ERD_pj.JWT.JWTUtil;
import com.example.ERD_pj.Service.ReissueService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReissueController {

  private final JWTUtil jwtUtil;
  private final ReissueService reissueService;

  public ReissueController(JWTUtil jwtUtil, ReissueService reissueService) {
    this.jwtUtil = jwtUtil;
    this.reissueService = reissueService;
  }

  @PostMapping("/reissue")
  public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
    return reissueService.reissue(request,response);
  }


}
