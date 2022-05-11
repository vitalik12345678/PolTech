package com.lpnu.poly.service.impl;

import com.lpnu.poly.DTO.post.CreatePostRequest;
import com.lpnu.poly.DTO.post.PostProfileResponse;
import com.lpnu.poly.DTO.post.UpdatePostRequest;
import com.lpnu.poly.entity.Post;
import com.lpnu.poly.repository.PostRepository;
import com.lpnu.poly.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public ResponseEntity<PostProfileResponse> getPost(Long id) {

        Post post = findById(id);
        PostProfileResponse postProfileResponse = new PostProfileResponse();

        return ResponseEntity.ok(postProfileResponse);
    }

    @Override
    public ResponseEntity<PostProfileResponse> deletePost(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<PostProfileResponse> createPost(CreatePostRequest createPostRequest) {
        return null;
    }

    @Override
    public ResponseEntity<PostProfileResponse> updatePost(UpdatePostRequest updatePostRequest) {
        return null;
    }

    private Post findById(Long id){
        return postRepository.findById(id).orElseThrow( () -> {
            throw new RuntimeException("User doesn't exist");
        });
    }

}
