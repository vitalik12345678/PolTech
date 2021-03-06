package com.lpnu.poly.controllers;

import com.lpnu.poly.DTO.users.UserCreateDTO;
import com.lpnu.poly.DTO.users.UserCurrentDTO;
import com.lpnu.poly.DTO.users.UserProfileDTO;
import com.lpnu.poly.DTO.users.UserUpdateDTO;
import com.lpnu.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<UserProfileDTO> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        return ResponseEntity.ok(userService.createUser(userCreateDTO));
    }


    @PutMapping(value = "/v1/")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<UserProfileDTO> updateUser(
            @RequestBody UserUpdateDTO userUpdateDTO) {
        return ResponseEntity.ok(userService.updateCurrentUser(userUpdateDTO));
    }

    @DeleteMapping(value = "/v1/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<UserProfileDTO> deleteUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping(value = "/v1/{id}")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<UserProfileDTO> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping(value = "/v1/current/")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<UserCurrentDTO> getCurrentUser() {
        return ResponseEntity.ok( userService.getCurrentUser());
    }

}
