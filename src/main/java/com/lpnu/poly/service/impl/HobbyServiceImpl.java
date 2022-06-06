package com.lpnu.poly.service.impl;

import com.lpnu.poly.entity.Hobby;
import com.lpnu.poly.repository.HobbyRepository;
import com.lpnu.poly.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HobbyServiceImpl implements HobbyService {

    private final HobbyRepository hobbyRepository;

    @Autowired
    public HobbyServiceImpl(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }


    @Override
    public ResponseEntity<List<String>> getAllHobby() {
        return ResponseEntity.ok( hobbyRepository.findAll().stream().map(Hobby::getName).collect(Collectors.toList()) );
    }
}
