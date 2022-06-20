package com.lpnu.poly.controllers;

import com.lpnu.poly.DTO.post.PostCreateRequest;
import com.lpnu.poly.DTO.post.PostProfileResponse;
import com.lpnu.poly.DTO.post.PostUpdateRequest;
import com.lpnu.poly.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/post/")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("v1/{id}")
    public ResponseEntity<PostProfileResponse> getPost(@PathVariable("id") Long id) {
        return postService.getPost(id);
    }

    @GetMapping("v1/allPost")
    public ResponseEntity<List<PostProfileResponse>> getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("v1/filter")
    public ResponseEntity<List<PostProfileResponse>> getFilteredPost(@RequestParam(value = "title",required = false) String title,
                                                                     @RequestParam(value = "days",required = false) String days,
                                                                     @RequestParam(value = "hobby",required = false) List<String> hobby,
                                                                     @RequestParam(value = "branch",required = false) List<String> branch,
                                                                     @RequestParam(value = "fromPage",required = false) String fromPage,
                                                                     @RequestParam(value = "pageCount",required = false) String pageCount) {
        return postService.getFilteredPost(title,days,branch,hobby,fromPage,pageCount);
    }

    @PostMapping("v1")
    public ResponseEntity<PostProfileResponse> createPost(@Valid @RequestBody PostCreateRequest postCreateRequest) {
        return postService.createPost(postCreateRequest);
    }

    @DeleteMapping("v1/{id}")
    public ResponseEntity<PostProfileResponse> deletePost(@PathVariable("id") Long id) {
        return postService.deletePost(id);
    }

    @PutMapping("v1/{id}")
    public ResponseEntity<PostProfileResponse> updatePost(@PathVariable("id") Long id, @RequestBody PostUpdateRequest postUpdateRequest) {
        return postService.updatePost(postUpdateRequest);
    }

}
