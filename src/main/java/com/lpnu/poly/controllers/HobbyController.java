package com.lpnu.poly.controllers;

import com.lpnu.poly.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/hobby/")
public class HobbyController {

    private final HobbyService hobbyService;

    @Autowired
    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @GetMapping("v1/allHobby")
    public ResponseEntity<List<String>> getAllHobby(){
        return hobbyService.getAllHobby();
    }

}
