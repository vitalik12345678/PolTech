package com.lpnu.poly.repository;

import com.lpnu.poly.entity.Hobby;
import com.lpnu.poly.entity.Post;
import com.lpnu.poly.entity.PostHobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PostHobbyRepository extends JpaRepository<PostHobby,Long> {

    List<PostHobby> findByPost(Post post);
    Set<PostHobby> findDistinctByHobbyIn(Set<Hobby> hobby);
}
