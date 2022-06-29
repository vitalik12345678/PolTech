package com.lpnu.poly.service.impl;

import com.lpnu.poly.DTO.comment.CommentCreateDTO;
import com.lpnu.poly.DTO.comment.CommentProfileDTO;
import com.lpnu.poly.entity.Comment;
import com.lpnu.poly.entity.Post;
import com.lpnu.poly.entity.User;
import com.lpnu.poly.entity.mapper.DTOConvertor;
import com.lpnu.poly.exception.NotExistsException;
import com.lpnu.poly.repository.CommentRepository;
import com.lpnu.poly.repository.PostRepository;
import com.lpnu.poly.repository.UserRepository;
import com.lpnu.poly.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CommentServiceImpl implements CommentService {

    private static final String USER_NOT_EXIST = "User doesn't exist";
    private static final String POST_NOT_EXIST = "Post doesn't exist";
    private static final String COMMENT_NOT_EXIST = "Comment doesn't exist";

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final DTOConvertor dtoConvertor;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, DTOConvertor dtoConvertor) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.dtoConvertor = dtoConvertor;
    }

    @Override
    public ResponseEntity<CommentProfileDTO> addComment(CommentCreateDTO commentCreateDTO) {

        User user = findUser(commentCreateDTO.getUserId());
        Post post = findPost(commentCreateDTO.getPostId());

        Comment comment = new Comment();

        comment.setUser(user);
        comment.setPost(post);
        comment.setDescription(commentCreateDTO.getContext());
        comment.setDate(LocalDateTime.now());

        commentRepository.save(comment);

        return ResponseEntity.ok(  dtoConvertor.convertToDTO(comment, new CommentProfileDTO()) );
    }

    @Override
    public ResponseEntity<List<CommentProfileDTO>> getCommentByPost(Long id) {
        List<Comment> comments = commentRepository.findByPost( findPost(id) );
        List<CommentProfileDTO> profiles = comments.stream().map
                (element -> dtoConvertor.convertToDTO(element,new CommentProfileDTO()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(profiles);
    }

    private User findUser(Long id){
        return userRepository.findById(id).orElseThrow( () -> {
            throw new NotExistsException(USER_NOT_EXIST);
        });
    }

    private Post findPost(Long id){
        return postRepository.findById(id).orElseThrow( () -> {
            throw new NotExistsException(POST_NOT_EXIST);
        });
    }

}
