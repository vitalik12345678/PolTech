package com.lpnu.poly.repository;

import com.lpnu.poly.entity.Post;
import com.lpnu.poly.entity.PostHobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostHobbyRepository extends JpaRepository<PostHobby,Long> {

    List<PostHobby> findByPost(Post post);

}
