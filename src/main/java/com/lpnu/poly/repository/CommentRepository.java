package com.lpnu.poly.repository;

import com.lpnu.poly.entity.Comment;
import com.lpnu.poly.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPost(Post post);

}
