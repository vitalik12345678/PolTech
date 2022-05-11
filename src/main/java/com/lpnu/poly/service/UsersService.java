package com.lpnu.poly.service;

import com.lpnu.poly.dto.users.UserCreateProfile;
import com.lpnu.poly.dto.users.UserGetProfile;
import com.lpnu.poly.dto.users.UsersUpdateProfile;
import org.springframework.http.ResponseEntity;
import com.lpnu.poly.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {

    ResponseEntity<List<UserGetProfile>> getAllUsers();

    ResponseEntity<UserGetProfile> findUser(Long id);

    ResponseEntity<?> deleteUser(Long id);

    ResponseEntity<UsersUpdateProfile> updateUser(Long id, UsersUpdateProfile playerUpdateProfile);

    ResponseEntity<UserCreateProfile> createUser(UserCreateProfile playerCreateProfile);

}
