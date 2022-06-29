package com.lpnu.poly.repository;

import com.lpnu.poly.entity.Post;
import com.lpnu.poly.entity.PostBranch;
import com.lpnu.poly.entity.PostHobby;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAll();

    Optional<Post> findByTitle(String title);

    Page<Post> findDistinctByTitleContainingIgnoreCaseAndPublishedDateGreaterThanAndPostBranchesInAndPostHobbiesIn(@Param("title") String title, @Param("publishedDate") LocalDateTime publishedDate, @Param("postBranches") Set<PostBranch> postBranches, @Param("postHobbies") Set<PostHobby> postHobbies, Pageable pageable);
}
