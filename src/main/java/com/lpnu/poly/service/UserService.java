package com.lpnu.poly.service;

import com.lpnu.poly.DTO.users.UserCreateRequest;
import com.lpnu.poly.DTO.users.UserProfileResponse;
import com.lpnu.poly.DTO.users.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<UserProfileResponse> getUser(String id);

    ResponseEntity<UserProfileResponse> deleteUser(Long id);

    ResponseEntity<UserProfileResponse> updateUser(UserUpdateRequest userUpdateProfile);

    ResponseEntity<UserProfileResponse> createUser(UserCreateRequest playerCreateProfile);

}
