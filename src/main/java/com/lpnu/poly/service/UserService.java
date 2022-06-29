package com.lpnu.poly.service;

import com.lpnu.poly.DTO.security.JWTResponse;
import com.lpnu.poly.DTO.security.LoginRequest;
import com.lpnu.poly.DTO.users.UserCreateDTO;
import com.lpnu.poly.DTO.users.UserCurrentDTO;
import com.lpnu.poly.DTO.users.UserProfileDTO;
import com.lpnu.poly.DTO.users.UserUpdateDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<UserProfileDTO> getUser(Long id);

    ResponseEntity<UserProfileDTO> deleteUser(Long id);

    ResponseEntity<UserProfileDTO> updateUser(UserUpdateDTO userUpdateProfile);

    ResponseEntity<UserProfileDTO> createUser(UserCreateDTO playerCreateProfile);

    ResponseEntity<UserCurrentDTO> getCurrentUser();

    ResponseEntity<JWTResponse> singin(LoginRequest loginRequest);

}
