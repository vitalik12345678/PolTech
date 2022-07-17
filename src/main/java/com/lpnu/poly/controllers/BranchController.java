package com.lpnu.poly.controllers;

import com.lpnu.poly.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/branch/")
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("v1/allBranch")
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    public ResponseEntity<List<String>> getAllBranch(){
        return branchService.getAllBranch();
    }

}

