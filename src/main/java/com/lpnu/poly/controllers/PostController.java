package com.lpnu.poly.controllers;

import com.lpnu.poly.DTO.post.PostCreateDTO;
import com.lpnu.poly.DTO.post.PostProfileDTO;
import com.lpnu.poly.DTO.post.PostUpdateDTO;
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
    public ResponseEntity<PostProfileDTO> getPost(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @GetMapping("v1/allPost")
    public ResponseEntity<List<PostProfileDTO>> getAllPost() {
        return ResponseEntity.ok(postService.getAllPost());
    }


    @GetMapping("v1/filter")
    public ResponseEntity<List<PostProfileDTO>> getFilteredPost(@RequestParam(value = "title", required = false) String title,
                                                                @RequestParam(value = "days", required = false) String days,
                                                                @RequestParam(value = "hobby", required = false) List<String> hobby,
                                                                @RequestParam(value = "branch", required = false) List<String> branch,
                                                                @RequestParam("page") int page,
                                                                @RequestParam("size") int size) {
        return ResponseEntity.ok(postService.getFilteredPost(title, days, branch, hobby, page, size));
    }

    @PostMapping("v1")
    public ResponseEntity<PostProfileDTO> createPost(@Valid @RequestBody PostCreateDTO postCreateDTO) {
        return ResponseEntity.ok(postService.createPost(postCreateDTO));
    }

    @DeleteMapping("v1/{id}")
    public ResponseEntity<PostProfileDTO> deletePost(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.deletePost(id));
    }

    @PutMapping("v1/{id}")
    public ResponseEntity<PostProfileDTO> updatePost(@PathVariable("id") Long id, @RequestBody PostUpdateDTO postUpdateDTO) {
        return ResponseEntity.ok(postService.updatePost(postUpdateDTO));
    }

}
