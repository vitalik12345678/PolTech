package com.lpnu.poly.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BranchService {

     ResponseEntity<List<String>> getAllBranch();

}
