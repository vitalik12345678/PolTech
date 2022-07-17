package com.lpnu.poly.service;

import com.lpnu.poly.DTO.security.JWTResponse;
import com.lpnu.poly.DTO.security.LoginRequest;
import com.lpnu.poly.DTO.users.UserCreateDTO;
import com.lpnu.poly.DTO.users.UserCurrentDTO;
import com.lpnu.poly.DTO.users.UserProfileDTO;
import com.lpnu.poly.DTO.users.UserUpdateDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    UserProfileDTO getUser(Long id);

    UserProfileDTO deleteUser(Long id);

    UserProfileDTO updateUser(UserUpdateDTO userUpdateProfile);

    UserProfileDTO createUser(UserCreateDTO playerCreateProfile);

    UserCurrentDTO getCurrentUser();

    JWTResponse singin(LoginRequest loginRequest);

}
