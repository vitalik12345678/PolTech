package com.lpnu.poly.controllers;

import com.lpnu.poly.DTO.comment.CommentCreateRequest;
import com.lpnu.poly.DTO.comment.CommentProfile;
import com.lpnu.poly.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comment/")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("v1/")
    public ResponseEntity<CommentProfile> addComment(@Valid @RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.addComment(commentCreateRequest);
    }

    @GetMapping("v1/byPost/{id}")
    public ResponseEntity<List<CommentProfile>> getCommentByPost(@PathVariable("id") Long id){
        return commentService.getCommentByPost(id);
    }


}
