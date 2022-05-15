package com.lpnu.poly.repository;

import com.lpnu.poly.entity.User;
import com.lpnu.poly.entity.UserHobby;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.remote.JMXPrincipal;
import java.util.List;

public interface UserHobbyRepository extends JpaRepository<UserHobby,Long> {

    List<UserHobby> findByUser(User user);

}
