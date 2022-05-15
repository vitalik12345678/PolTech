package com.lpnu.poly.repository;

import com.lpnu.poly.entity.Post;
import com.lpnu.poly.entity.PostBranch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostBranchRepository extends JpaRepository<PostBranch,Long> {

    List<PostBranch> findByPost(Post post);

}
