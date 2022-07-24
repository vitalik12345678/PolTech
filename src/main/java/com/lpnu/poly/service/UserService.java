package com.lpnu.poly.service;

import com.lpnu.poly.DTO.security.JWTResponse;
import com.lpnu.poly.DTO.security.LoginRequest;
import com.lpnu.poly.DTO.users.UserCreateDTO;
import com.lpnu.poly.DTO.users.UserCurrentDTO;
import com.lpnu.poly.DTO.users.UserProfileDTO;
import com.lpnu.poly.DTO.users.UserUpdateDTO;

public interface UserService {

    UserProfileDTO getUser(Long id);

    UserProfileDTO deleteUser(Long id);

    UserProfileDTO createUser(UserCreateDTO playerCreateProfile);

    UserCurrentDTO getCurrentUser();

    UserProfileDTO updateCurrentUser(UserUpdateDTO userUpdateDTO);

    JWTResponse singin(LoginRequest loginRequest);

}
