package com.lpnu.poly.service;

import com.lpnu.poly.dto.users.UserCreateProfile;
import com.lpnu.poly.dto.users.UsersUpdateProfile;
import org.springframework.http.ResponseEntity;
import com.lpnu.poly.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<User> findUser(Long id);

    ResponseEntity<User> deleteUser(Long id);

    ResponseEntity<User> updateUser(Long id, UsersUpdateProfile playerUpdateProfile);

    ResponseEntity<User> createUser(UserCreateProfile playerCreateProfile);

}
