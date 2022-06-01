package com.lpnu.poly.repository;

import com.lpnu.poly.entity.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAll();

    Optional<Post> findByTitle(String title);

    Set<Post> findByTitleIsContainingAndPublishedDateGreaterThanAndPostBranchesInAndPostHobbiesIn(@Param("title") String title, @Param("publishedDate") LocalDateTime publishedDate, @Param("postBranches") Set<PostBranch> postBranches, @Param("postHobbies") Set<PostHobby> postHobbies);
}
