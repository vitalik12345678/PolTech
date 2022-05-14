package com.lpnu.poly.service;

import com.lpnu.poly.DTO.users.UserCreateRequest;
import com.lpnu.poly.DTO.users.UserProfileResponse;
import com.lpnu.poly.DTO.users.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    ResponseEntity<UserProfileResponse> getUser(Long id);

    ResponseEntity<UserProfileResponse> deleteUser(Long id);

    ResponseEntity<UserUpdateRequest> updateUser(UserUpdateRequest userUpdateProfile);

    ResponseEntity<UserCreateRequest> createUser(UserCreateRequest playerCreateProfile);

}
