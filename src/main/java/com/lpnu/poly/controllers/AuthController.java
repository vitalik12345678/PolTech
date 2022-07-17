package com.lpnu.poly.controllers;

import com.lpnu.poly.DTO.security.JWTResponse;
import com.lpnu.poly.DTO.security.LoginRequest;
import com.lpnu.poly.DTO.users.UserCreateDTO;
import com.lpnu.poly.DTO.users.UserProfileDTO;
import com.lpnu.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signin")
    public ResponseEntity<JWTResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.singin(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserProfileDTO> registerUser(@RequestBody UserCreateDTO signUpRequest) {
      return  ResponseEntity.ok( userService.createUser(signUpRequest));
    }
}
