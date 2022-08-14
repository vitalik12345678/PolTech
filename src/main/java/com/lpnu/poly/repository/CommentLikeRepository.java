package com.lpnu.poly.repository;

import com.lpnu.poly.entity.Comment;
import com.lpnu.poly.entity.CommentLike;
import com.lpnu.poly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike ,Long> {

    Optional<CommentLike> findByUserAndComment(User user, Comment comment);

}
