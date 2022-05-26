package com.lpnu.poly.service;

import com.lpnu.poly.DTO.security.JWTResponse;
import com.lpnu.poly.DTO.security.LoginRequest;
import com.lpnu.poly.DTO.users.UserCreateRequest;
import com.lpnu.poly.DTO.users.UserCurrentResponse;
import com.lpnu.poly.DTO.users.UserProfileResponse;
import com.lpnu.poly.DTO.users.UserUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<UserProfileResponse> getUser(Long id);

    ResponseEntity<UserProfileResponse> deleteUser(Long id);

    ResponseEntity<UserProfileResponse> updateUser(UserUpdateRequest userUpdateProfile);

    ResponseEntity<UserProfileResponse> createUser(UserCreateRequest playerCreateProfile);

    ResponseEntity<UserCurrentResponse> getCurrentUser();

    ResponseEntity<JWTResponse> singin(LoginRequest loginRequest);

}
