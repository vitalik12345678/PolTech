package com.lpnu.poly.repository;

import com.lpnu.poly.entity.Post;
import com.lpnu.poly.entity.PostLike;
import com.lpnu.poly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike , Long> {

    @Override
    Optional<PostLike> findById(Long id);

    Optional<PostLike> findByPostAndUser(Post post, User user);

    Long countByPost(Post post);

    boolean existsByPostAndUser(Post post, User user);

}
