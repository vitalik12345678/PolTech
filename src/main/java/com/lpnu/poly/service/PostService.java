package com.lpnu.poly.service;

import com.lpnu.poly.DTO.post.PostCreateDTO;
import com.lpnu.poly.DTO.post.PostProfileDTO;
import com.lpnu.poly.DTO.post.PostUpdateDTO;

import java.util.List;

public interface PostService {

    PostProfileDTO getPost(Long id);

    PostProfileDTO deletePost(Long id);

    PostProfileDTO createPost(PostCreateDTO postCreateDTO);

    PostProfileDTO updatePost(PostUpdateDTO postUpdateDTO);

    List<PostProfileDTO> getAllPost();

    List<PostProfileDTO> getFilteredPost(String title, String days, List<String> branches, List<String> hobby, /*Pageable pageable*/int page, int size);

    Boolean createLike(Long postId);

    Boolean deleteLike(Long postId);
}
