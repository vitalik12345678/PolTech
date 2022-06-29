package com.lpnu.poly.service.impl;

import com.lpnu.poly.DTO.post.PostCreateDTO;
import com.lpnu.poly.DTO.post.PostProfileDTO;
import com.lpnu.poly.DTO.post.PostUpdateDTO;
import com.lpnu.poly.entity.*;
import com.lpnu.poly.entity.mapper.DTOConvertor;
import com.lpnu.poly.exception.ExistsException;
import com.lpnu.poly.exception.NotExistsException;
import com.lpnu.poly.repository.*;
import com.lpnu.poly.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PostServiceImpl implements PostService {

    private static final String USER_NOT_EXIST = "User doesn't exist ";
    private static final String POST_NOT_EXIST = "Post doesn't exist ";
    private static final String BRANCH_NOT_EXIST = "Branch doesn't exist ";
    private static final String HOBBY_NOT_EXIST = "Hobby doesn't exist ";
    private static final String POST_EXIST = "Post exists ";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostHobbyRepository postHobbyRepository;
    private final PostBranchRepository postBranchRepository;
    private final DTOConvertor dtoConvertor;
    private final BranchRepository branchRepository;
    private final HobbyRepository hobbyRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, PostHobbyRepository postHobbyRepository, PostBranchRepository postBranchRepository, DTOConvertor dtoConvertor, BranchRepository branchRepository, HobbyRepository hobbyRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postHobbyRepository = postHobbyRepository;
        this.postBranchRepository = postBranchRepository;
        this.dtoConvertor = dtoConvertor;
        this.branchRepository = branchRepository;
        this.hobbyRepository = hobbyRepository;
    }

    @Override
    public PostProfileDTO getPost(Long id) {
        return dtoConvertor.convertToDTO(findPost(id), new PostProfileDTO());
    }

    @Override
    public PostProfileDTO deletePost(Long id) {
        Post post = findPost(id);
        postRepository.delete(post);
        return dtoConvertor.convertToDTO(post, new PostProfileDTO());
    }

    @Override
    public PostProfileDTO createPost(PostCreateDTO postCreateDTO) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Post> optionalPost = postRepository.findByTitle(postCreateDTO.getTitle());
        if (optionalPost.isPresent()) {
            throw new ExistsException(POST_EXIST);
        }

        User user = findUser(userDetails.getUsername());

        Post post = dtoConvertor.convertToEntity(postCreateDTO, new Post());
        post.setUser(user);
        post.setPostHobbies(getPostHobbyFromClient(post, postCreateDTO.getHobby()));
        post.setPostBranches(getPostBranchesFromClient(post, postCreateDTO.getBranch()));
        post.setPublishedDate(LocalDateTime.now());

        postRepository.save(post);
        postBranchRepository.saveAll(getPostBranchesFromClient(post, postCreateDTO.getBranch()));
        postHobbyRepository.saveAll(getPostHobbyFromClient(post, postCreateDTO.getHobby()));

        return dtoConvertor.convertToDTO(post, new PostProfileDTO());
    }

    @Override
    public PostProfileDTO updatePost(PostUpdateDTO postUpdateDTO) {

        Post post = findPost(postUpdateDTO.getTitle());

        post.setPostBranches(getPostBranchesFromClient(post, postUpdateDTO.getBranch()));
        post.setPostHobbies(getPostHobbyFromClient(post, postUpdateDTO.getHobby()));

        postHobbyRepository.deleteAll(postHobbyRepository.findByPost(post));
        postBranchRepository.deleteAll(postBranchRepository.findByPost(post));

        postHobbyRepository.saveAll(getPostHobbyFromClient(post, postUpdateDTO.getHobby()));
        postBranchRepository.saveAll(getPostBranchesFromClient(post, postUpdateDTO.getBranch()));

        postRepository.save(post);
        return dtoConvertor.convertToDTO(post, new PostProfileDTO());
    }

    @Override
    public List<PostProfileDTO> getAllPost() {
        return postRepository.findAll().stream().map(element -> dtoConvertor.convertToDTO(element, new PostProfileDTO())).collect(Collectors.toList());
    }

    @Override
    public List<PostProfileDTO> getFilteredPost(String title, String days, List<String> branches, List<String> hobby,/* Pageable pageable*/int page, int size) {


        List<Branch> branchList = branches.stream().map(element ->
                branchRepository.findByName(element).orElseThrow(() -> {
                    throw new NotExistsException(BRANCH_NOT_EXIST + element);
                })
        ).collect(Collectors.toList());

        Set<PostBranch> postBranches = postBranchRepository.findDistinctByBranchIn(branchList);

        Set<Hobby> hobbies = hobby.stream().map(element ->
                hobbyRepository.findByName(element).orElseThrow(() -> {
                    throw new NotExistsException(BRANCH_NOT_EXIST + element);
                })).collect(Collectors.toSet());

        Set<PostHobby> postHobbies = postHobbyRepository.findDistinctByHobbyIn(hobbies);

        Pageable pageable = PageRequest.of((page), (size), Sort.by("publishedDate").descending());

        Page<Post> resultPosts = postRepository.findDistinctByTitleContainingIgnoreCaseAndPublishedDateGreaterThanAndPostBranchesInAndPostHobbiesIn(title, LocalDateTime.parse(days), postBranches, postHobbies, pageable);

        return resultPosts.getContent().stream().map(element -> dtoConvertor.convertToDTO(element, new PostProfileDTO())).collect(Collectors.toList());
    }

    private List<PostHobby> getPostHobbyFromClient(Post post, List<String> hobby) {
        return hobby.stream().map(hobbyName -> {
            PostHobby postHobby = new PostHobby();
            postHobby.setPost(post);
            postHobby.setHobby(findHobby(hobbyName));
            return postHobby;
        }).collect(Collectors.toList());
    }

    private List<PostBranch> getPostBranchesFromClient(Post post, List<String> branch) {
        return branch.stream().map(branchName -> {
            PostBranch postBranch = new PostBranch();
            postBranch.setPost(post);
            postBranch.setBranch(findBranch(branchName));
            return postBranch;
        }).collect(Collectors.toList());
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> {
            throw new NotExistsException(POST_NOT_EXIST);
        });
    }

    private Post findPost(String title) {
        return postRepository.findByTitle(title).orElseThrow(() -> {
            throw new NotExistsException(POST_NOT_EXIST);
        });
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new NotExistsException(USER_NOT_EXIST);
        });
    }

    private User findUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            throw new NotExistsException(USER_NOT_EXIST);
        });
    }

    private Branch findBranch(String name) {
        return branchRepository.findByName(name).orElseThrow(() -> {
            throw new NotExistsException(BRANCH_NOT_EXIST);
        });
    }

    private Hobby findHobby(String name) {
        return hobbyRepository.findByName(name).orElseThrow(() -> {
            throw new NotExistsException(HOBBY_NOT_EXIST);
        });
    }

}
