package com.lpnu.poly.service;

import com.lpnu.poly.DTO.comment.CommentCreateRequest;
import com.lpnu.poly.DTO.comment.CommentProfile;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    ResponseEntity<CommentProfile> addComment(CommentCreateRequest commentCreateRequest);

    ResponseEntity<List<CommentProfile>> getCommentByPost(Long id);

}
