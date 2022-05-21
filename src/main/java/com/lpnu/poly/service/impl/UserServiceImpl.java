package com.lpnu.poly.service.impl;

import com.lpnu.poly.DTO.users.UserCreateRequest;
import com.lpnu.poly.DTO.users.UserProfileResponse;
import com.lpnu.poly.DTO.users.UserUpdateRequest;
import com.lpnu.poly.entity.*;
import com.lpnu.poly.entity.mapper.DTOConvertor;
import com.lpnu.poly.exception.ExistsException;
import com.lpnu.poly.exception.NotExistsException;
import com.lpnu.poly.repository.*;
import com.lpnu.poly.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final BranchRepository branchRepository;
    private final UserRepository userRepository;
    private final DTOConvertor dtoConvertor;
    private final HobbyRepository hobbyRepository;
    private final UserBranchRepository userBranchRepository;
    private final UserHobbyRepository userHobbyRepository;
    private static final String USER_IS_NOT_EXIST = "User not exist";
    private static final String BRANCH_NOT_EXIST = "Branch doesn't exist";
    private static final String HOBBY_NOT_EXIST = "Hobby doesn't exist";
    private static final String USER_EXIST = "User exist";


    @Autowired
    public UserServiceImpl(BranchRepository branchRepository, UserRepository usersRepository, DTOConvertor dtoConvertor, HobbyRepository hobbyRepository, UserBranchRepository userBranchRepository, UserHobbyRepository userHobbyRepository) {
        this.branchRepository = branchRepository;
        this.userRepository = usersRepository;
        this.dtoConvertor = dtoConvertor;
        this.hobbyRepository = hobbyRepository;
        this.userBranchRepository = userBranchRepository;
        this.userHobbyRepository = userHobbyRepository;
    }


    @Override
    public ResponseEntity<UserProfileResponse> getUser(String email) {
        User user = findUser(email);
        UserProfileResponse userProfileResponse = dtoConvertor.convertToDTO(user, new UserProfileResponse());
        return ResponseEntity.ok(userProfileResponse);
    }

    @Override
    public ResponseEntity<UserProfileResponse> deleteUser(Long id) {
        User user = findUser(id);
        UserProfileResponse userProfileResponse = dtoConvertor.convertToDTO(user, new UserProfileResponse());
        userRepository.delete(user);
        return ResponseEntity.ok(userProfileResponse);
    }

        @Override
    public ResponseEntity<UserProfileResponse> updateUser(UserUpdateRequest userUpdateRequest) {
        User user = findUser(userUpdateRequest.getEmail());

        user.setUserBranches(getUserBranchesFromClient(user, userUpdateRequest.getBranch()));
        user.setUserHobbies(getUserHobbyFromClient(user, userUpdateRequest.getHobby()));

       userHobbyRepository.deleteAll(userHobbyRepository.findByUser(user));
       userBranchRepository.deleteAll(userBranchRepository.findByUser(user));

       userBranchRepository.saveAll(getUserBranchesFromClient(user,userUpdateRequest.getBranch()));
       userHobbyRepository.saveAll(getUserHobbyFromClient(user,userUpdateRequest.getHobby()));

       userRepository.save(user);

        return ResponseEntity.ok(dtoConvertor.convertToDTO(user,new UserProfileResponse()));
    }


    @Override
    public ResponseEntity<UserProfileResponse> createUser(UserCreateRequest userCreateRequest) {

        Optional<User> optionalUser = userRepository.findByEmail(userCreateRequest.getEmail());

        if (optionalUser.isPresent()){
            throw new ExistsException(USER_EXIST);
        }

        User user = dtoConvertor.convertToEntity(userCreateRequest,new User());
        user.setUserBranches(getUserBranchesFromClient(user,userCreateRequest.getBranch()));
        user.setUserHobbies(getUserHobbyFromClient(user,userCreateRequest.getHobby()));
        userRepository.save(user);
        userHobbyRepository.saveAll(getUserHobbyFromClient(user,userCreateRequest.getHobby()));
        userBranchRepository.saveAll(getUserBranchesFromClient(user,userCreateRequest.getBranch()));
        return ResponseEntity.ok(dtoConvertor.convertToDTO(user,new UserProfileResponse()));
    }

    private List<UserHobby> getUserHobbyFromClient(User user, List<String> hobby) {
        List<UserHobby> postHobbies = new ArrayList<>();

        hobby.forEach(hobbyName -> {

            UserHobby userHobby = new UserHobby();
            userHobby.setUser(user);
            userHobby.setHobby(findHobby(hobbyName));
            postHobbies.add(userHobby);

        });
        return postHobbies;
    }

    private List<UserBranch> getUserBranchesFromClient(User user, List<String> branch) {
        List<UserBranch> userBranches = new ArrayList<>();

        branch.forEach(branchName -> {

            UserBranch userBranch = new UserBranch();
            userBranch.setUser(user);
            userBranch.setBranch(findBranch(branchName));
            userBranches.add(userBranch);

        });
        return userBranches;
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new NotExistsException(USER_IS_NOT_EXIST);
        });
    }

    private User findUser(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> {
            throw new NotExistsException(USER_IS_NOT_EXIST);
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
