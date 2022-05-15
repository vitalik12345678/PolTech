package com.lpnu.poly.repository;

import com.lpnu.poly.entity.User;
import com.lpnu.poly.entity.UserBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserBranchRepository extends JpaRepository<UserBranch,Long> {

    List<UserBranch> findByUser(User user);

    void deleteByUser(User user);

}
