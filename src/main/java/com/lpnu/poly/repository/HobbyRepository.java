package com.lpnu.poly.repository;

import com.lpnu.poly.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby,Long> {

    @Override
    Optional<Hobby> findById(Long id);

    Optional<Hobby> findByName(String name);



}
