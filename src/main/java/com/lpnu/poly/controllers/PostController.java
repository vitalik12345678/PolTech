package com.lpnu.poly.controllers;

import com.lpnu.poly.DTO.post.PostCreateRequest;
import com.lpnu.poly.DTO.post.PostProfileResponse;
import com.lpnu.poly.DTO.post.PostUpdateRequest;
import com.lpnu.poly.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/post/")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("v1/{id}")
    public ResponseEntity<PostProfileResponse> getPost(@PathVariable("id")Long id ){
        return postService.getPost(id);
    }

    @PostMapping("v1")
    public ResponseEntity<PostProfileResponse> createPost(@Valid @RequestBody PostCreateRequest postCreateRequest){
        return postService.createPost(postCreateRequest);
    }

    @DeleteMapping("v1/{id}")
    public ResponseEntity<PostProfileResponse> deletePost(@PathVariable("id")Long id){
        return postService.deletePost(id);
    }

    @PutMapping("v1/{id}")
    public ResponseEntity<PostProfileResponse> updatePost(@PathVariable("id")Long id,
                                                          @RequestBody PostUpdateRequest postUpdateRequest){
        return postService.updatePost(postUpdateRequest);
    }

}
