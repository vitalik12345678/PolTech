package com.lpnu.poly.controllers;

import com.lpnu.poly.DTO.post.PostProfileResponse;
import com.lpnu.poly.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
