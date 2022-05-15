package com.lpnu.poly.service.impl;

import com.lpnu.poly.DTO.post.PostCreateRequest;
import com.lpnu.poly.DTO.post.PostProfileResponse;
import com.lpnu.poly.DTO.post.PostUpdateRequest;
import com.lpnu.poly.entity.*;
import com.lpnu.poly.entity.mapper.DTOConvertor;
import com.lpnu.poly.exception.NotExistsException;
import com.lpnu.poly.repository.*;
import com.lpnu.poly.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class PostServiceImpl implements PostService {

    private static final String USER_NOT_EXIST = "User doesn't exist";
    private static final String POST_NOT_EXIST = "Post doesn't exist";
    private static final String BRANCH_NOT_EXIST = "Branch doesn't exist";
    private static final String HOBBY_NOT_EXIST = "Hobby doesn't exist";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostHobbyRepository postHobbyRepository;
    private final PostBranchRepository postBranchRepository;
    private final DTOConvertor dtoConvertor;
    private final BranchRepository branchRepository;
    private final HobbyRepository hobbyRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, PostHobbyRepository postHobbyRepository, PostBranchRepository postBranchRepository, DTOConvertor dtoConvertor, BranchRepository branchRepository, HobbyRepository hobbyRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postHobbyRepository = postHobbyRepository;
        this.postBranchRepository = postBranchRepository;
        this.dtoConvertor = dtoConvertor;
        this.branchRepository = branchRepository;
        this.hobbyRepository = hobbyRepository;
    }

    @Override
    public ResponseEntity<PostProfileResponse> getPost(Long id) {
        Post post = findPost(id);
        PostProfileResponse postProfileResponse = dtoConvertor.convertToDTO(post, new PostProfileResponse());
        return ResponseEntity.ok(postProfileResponse);
    }

    @Override
    public ResponseEntity<PostProfileResponse> deletePost(Long id) {
        Post post = findPost(id);
        PostProfileResponse postProfileResponse = dtoConvertor.convertToDTO(post, new PostProfileResponse());
        postRepository.delete(post);
        return ResponseEntity.ok(postProfileResponse);
    }

    @Override
    public ResponseEntity<PostProfileResponse> createPost(PostCreateRequest postCreateRequest) {
        User user = findUser(postCreateRequest.getEmail());

        Post post = new Post();
        post.setUser(user);
        post.setPostHobbies(getPostHobbyFromClient(post, postCreateRequest.getHobby()));
        post.setPostBranches(getPostBranchesFromClient(post, postCreateRequest.getBranch()));
        post.setTitle(postCreateRequest.getTitle());
        post.setPublishedDate(LocalDateTime.now());

        postBranchRepository.saveAll(getPostBranchesFromClient(post,postCreateRequest.getBranch()));
        postHobbyRepository.saveAll(getPostHobbyFromClient(post,postCreateRequest.getHobby()));
        postRepository.save(post);


        return ResponseEntity.ok(new PostProfileResponse(post.getTitle()));
    }

    @Override
    public ResponseEntity<PostProfileResponse> updatePost(PostUpdateRequest postUpdateRequest) {

        Post post = findPost(postUpdateRequest.getTitle());

        post.setPostBranches(getPostBranchesFromClient(post, postUpdateRequest.getBranch()));
        post.setPostHobbies(getPostHobbyFromClient(post, postUpdateRequest.getHobby()));

        postHobbyRepository.deleteAll(postHobbyRepository.findByPost(post));
        postBranchRepository.deleteAll(postBranchRepository.findByPost(post));

        postHobbyRepository.saveAll(getPostHobbyFromClient(post,postUpdateRequest.getHobby()));
        postBranchRepository.saveAll(getPostBranchesFromClient(post,postUpdateRequest.getBranch()));

        postRepository.save(post);
        return ResponseEntity.ok(new PostProfileResponse(postUpdateRequest.getTitle()));
    }

    private List<PostHobby> getPostHobbyFromClient(Post post, List<String> hobby) {
        List<PostHobby> postHobbies = new ArrayList<>();

        hobby.forEach(hobbyName -> {

            PostHobby postHobby = new PostHobby();
            postHobby.setPost(post);
            postHobby.setHobby(findHobby(hobbyName));
            postHobbies.add(postHobby);

        });
        return postHobbies;
    }

    private List<PostBranch> getPostBranchesFromClient(Post post, List<String> branch) {
        List<PostBranch> postBranches = new ArrayList<>();

        branch.forEach(branchName -> {

            PostBranch postBranch = new PostBranch();
            postBranch.setPost(post);
            postBranch.setBranch(findBranch(branchName));
            postBranches.add(postBranch);

        });
        return postBranches;
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> {
            throw new NotExistsException(POST_NOT_EXIST);
        });
    }

    private Post findPost(String title) {
        return postRepository.findByTitle(title).orElseThrow(() -> {
            throw new NotExistsException(POST_NOT_EXIST);
        });
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new NotExistsException(USER_NOT_EXIST);
        });
    }

    private User findUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            throw new NotExistsException(USER_NOT_EXIST);
        });
    }

    private Branch findBranch(String name) {
        return branchRepository.findByName(name).orElseThrow(() -> {
            throw new NotExistsException(BRANCH_NOT_EXIST);
        });
    }

    private Hobby findHobby(String name) {
        return hobbyRepository.findByName(name).orElseThrow(() -> {
            throw new NotExistsException(HOBBY_NOT_EXIST);
        });
    }

}
