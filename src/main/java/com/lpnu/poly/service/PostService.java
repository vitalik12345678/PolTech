package com.lpnu.poly.service;

import com.lpnu.poly.DTO.post.CreatePostRequest;
import com.lpnu.poly.DTO.post.PostProfileResponse;
import com.lpnu.poly.DTO.post.UpdatePostRequest;
import org.springframework.http.ResponseEntity;

public interface PostService {

    ResponseEntity<PostProfileResponse> getPost(Long id);

    ResponseEntity<PostProfileResponse> deletePost(Long id);

    ResponseEntity<PostProfileResponse> createPost(CreatePostRequest createPostRequest);

    ResponseEntity<PostProfileResponse> updatePost(UpdatePostRequest updatePostRequest);

}
