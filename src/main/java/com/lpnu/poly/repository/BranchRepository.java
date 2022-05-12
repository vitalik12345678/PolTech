package com.lpnu.poly.repository;

import com.lpnu.poly.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    @Override
    Optional<Branch> findById(Long id);

    Optional<Branch> findByName (String name);
}
