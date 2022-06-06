package com.lpnu.poly.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HobbyService {

    ResponseEntity<List<String>> getAllHobby();

}
