package com.lpnu.poly.controllers;

import com.lpnu.poly.DTO.comment.CommentCreateDTO;
import com.lpnu.poly.DTO.comment.CommentProfileDTO;
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
    public ResponseEntity<CommentProfileDTO> addComment(@Valid @RequestBody CommentCreateDTO commentCreateDTO){
        return commentService.addComment(commentCreateDTO);
    }

    @GetMapping("v1/byPost/{id}")
    public ResponseEntity<List<CommentProfileDTO>> getCommentByPost(@PathVariable("id") Long id){
        return commentService.getCommentByPost(id);
    }

}
