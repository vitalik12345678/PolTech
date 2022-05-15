package com.lpnu.poly.controllers;

import com.lpnu.poly.DTO.users.UserCreateRequest;
import com.lpnu.poly.DTO.users.UserProfileResponse;
import com.lpnu.poly.DTO.users.UserUpdateRequest;
import com.lpnu.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

        private final UserService userService;

        @Autowired
        public UserController(UserService userService) {
            this.userService = userService;
        }

        @PostMapping(value = "/v1/")
        public ResponseEntity<UserProfileResponse> createUser(@RequestBody UserCreateRequest userCreateRequest){
            return userService.createUser(userCreateRequest);
        }


        @PutMapping(value = "/v1/")
        public ResponseEntity<UserProfileResponse> updateUser(
                                                         //   @Valid
                                                    @RequestBody
                                                                    UserUpdateRequest userUpdateRequest){
            return userService.updateUser(userUpdateRequest);
        }

        @DeleteMapping(value = "/v1/{id}")
        public ResponseEntity<UserProfileResponse> deleteUser(@PathVariable("id") Long id){
            return userService.deleteUser(id);
        }

        @GetMapping(value = "/v1/{email}")
        public ResponseEntity<UserProfileResponse> getUser(@PathVariable("email")String email){
            return userService.getUser(email);
        }
}
