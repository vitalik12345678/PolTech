package com.lpnu.poly.service;

import com.lpnu.poly.DTO.post.PostCreateRequest;
import com.lpnu.poly.DTO.post.PostProfileResponse;
import com.lpnu.poly.DTO.post.PostUpdateRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    ResponseEntity<PostProfileResponse> getPost(Long id);

    ResponseEntity<PostProfileResponse> deletePost(Long id);

    ResponseEntity<PostProfileResponse> createPost(PostCreateRequest postCreateRequest);

    ResponseEntity<PostProfileResponse> updatePost(PostUpdateRequest postUpdateRequest);

    ResponseEntity<List<PostProfileResponse>> getAllPost();
}
