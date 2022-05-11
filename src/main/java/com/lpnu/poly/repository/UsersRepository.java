package com.lpnu.poly.repository;

import com.lpnu.poly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User,Long> {

    @Override
    Optional<User> findById(Long id);

    @Override
    List<User> findAll();
}
