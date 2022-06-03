package com.lpnu.poly.repository;

import com.lpnu.poly.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PostBranchRepository extends JpaRepository<PostBranch,Long> {

    List<PostBranch> findByPost(Post post);

    Set<PostBranch> findDistinctByBranchIn(Collection<Branch> hobby);

}
