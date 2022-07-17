package com.lpnu.poly.service;

import com.lpnu.poly.DTO.comment.CommentCreateDTO;
import com.lpnu.poly.DTO.comment.CommentProfileDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    ResponseEntity<CommentProfileDTO> addComment(CommentCreateDTO commentCreateDTO);

    ResponseEntity<List<CommentProfileDTO>> getCommentByPost(Long id);

    CommentProfileDTO deleteComment(Long id);

    CommentProfileDTO updateComment(Long id);
}
