package com.lpnu.poly.repository;

import com.lpnu.poly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByAllUp(Integer graduationYear, String graduate, String work, String hobby, String firstName, String lastName, String middleName);

    Optional<User> findByAllCreate(String email, String password, Integer graduationYear, @NotBlank List<String> graduate, String work, String hobby, String firstName, String lastName, String middleName);

    Optional<User> findByEmail(String email);
}
