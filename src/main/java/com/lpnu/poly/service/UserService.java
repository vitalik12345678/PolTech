package com.lpnu.poly.service;

import com.lpnu.poly.dto.users.UserCreateRequest;
import com.lpnu.poly.dto.users.UserProfileResponse;
import com.lpnu.poly.dto.users.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    ResponseEntity<List<UserProfileResponse>> getAllUsers();

    ResponseEntity<UserProfileResponse> findUser(Long id);

    ResponseEntity<UserProfileResponse> deleteUser(Long id);

    ResponseEntity<UserUpdateRequest> updateUser(Long id, UserUpdateRequest playerUpdateProfile);

    ResponseEntity<UserCreateRequest> createUser(UserCreateRequest playerCreateProfile);

}
