package com.lpnu.poly.controllers;

import com.lpnu.poly.dto.users.UserCreateProfile;
import com.lpnu.poly.dto.users.UserGetProfile;
import com.lpnu.poly.dto.users.UsersUpdateProfile;
import com.lpnu.poly.entity.User;
import com.lpnu.poly.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

        private final UsersService usersService;

        @Autowired
        public UserController(UsersService usersService) {
            this.usersService = usersService;
        }

        @PostMapping(value = "/v1/")
        public ResponseEntity<UserCreateProfile> createUser(@Valid @RequestBody UserCreateProfile userCreateProfile){
            return usersService.createUser(userCreateProfile);
        }


        @PutMapping(value = "/v1/{id}")
        public ResponseEntity<UsersUpdateProfile> updateUser(@PathVariable("id") Long id,
                                                    @Valid
                                                    @RequestBody
                                                            UsersUpdateProfile usersUpdateProfile){
            return usersService.updateUser(id,usersUpdateProfile);
        }

        @DeleteMapping(value = "/v1/{id}")
        public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
            return usersService.deleteUser(id);
        }

        @GetMapping(value = "/v1/allUsers")
        public ResponseEntity<List<UserGetProfile>> getAllUsers(){
            return usersService.getAllUsers();
        }

        @GetMapping(value = "/v1/{id}")
        public ResponseEntity<UserGetProfile> findUser(@PathVariable("id")Long id){
            return usersService.findUser(id);
        }
}
