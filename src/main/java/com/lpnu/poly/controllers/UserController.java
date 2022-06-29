package com.lpnu.poly.controllers;

import com.lpnu.poly.DTO.users.UserCreateDTO;
import com.lpnu.poly.DTO.users.UserCurrentDTO;
import com.lpnu.poly.DTO.users.UserProfileDTO;
import com.lpnu.poly.DTO.users.UserUpdateDTO;
import com.lpnu.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/v1/")
    public ResponseEntity<UserProfileDTO> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }


    @PutMapping(value = "/v1/")
    public ResponseEntity<UserProfileDTO> updateUser(
            //   @Valid
            @RequestBody
                    UserUpdateDTO userUpdateDTO) {
        return userService.updateUser(userUpdateDTO);
    }

    @DeleteMapping(value = "/v1/{id}")
    public ResponseEntity<UserProfileDTO> deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping(value = "/v1/{id}")
    public ResponseEntity<UserProfileDTO> getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @GetMapping(value = "/v1/current/")
    public ResponseEntity<UserCurrentDTO> getCurrentUser() {
        return userService.getCurrentUser();
    }

}
