package com.lpnu.poly.service.impl;

import com.lpnu.poly.entity.Branch;
import com.lpnu.poly.repository.BranchRepository;
import com.lpnu.poly.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public ResponseEntity<List<String>> getAllBranch() {
        return ResponseEntity.ok(branchRepository.findAll().stream().map(Branch::getName).collect(Collectors.toList()));
    }
}
