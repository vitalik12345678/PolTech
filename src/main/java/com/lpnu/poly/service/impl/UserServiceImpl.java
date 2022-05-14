package com.lpnu.poly.service.impl;

import com.lpnu.poly.DTO.users.UserCreateRequest;
import com.lpnu.poly.DTO.users.UserProfileResponse;
import com.lpnu.poly.DTO.users.UserUpdateRequest;
import com.lpnu.poly.entity.*;
import com.lpnu.poly.entity.mapper.DTOConvertor;
import com.lpnu.poly.exception.NotExistsException;
import com.lpnu.poly.repository.BranchRepository;
import com.lpnu.poly.repository.HobbyRepository;
import com.lpnu.poly.repository.UserRepository;
import com.lpnu.poly.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final BranchRepository branchRepository;
    private final UserRepository userRepository;
    private final DTOConvertor dtoConvertor;
    private final HobbyRepository hobbyRepository;
    private final String USER_IS_NOT_EXIST = "User not exist";
    private final String USER_IS_NOT_FOUND = "User wasn't found";
    private static final String BRANCH_NOT_EXIST = "Branch doesn't exist";
    private static final String HOBBY_NOT_EXIST = "Hobby doesn't exist";


    @Autowired
    public UserServiceImpl(BranchRepository branchRepository, UserRepository usersRepository, DTOConvertor dtoConvertor, HobbyRepository hobbyRepository) {
        this.branchRepository = branchRepository;
        this.userRepository = usersRepository;
        this.dtoConvertor = dtoConvertor;
        this.hobbyRepository = hobbyRepository;
    }


    @Override
    public ResponseEntity<UserProfileResponse> getUser(Long id) {
        User user = findUser(id);
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
    public ResponseEntity<UserUpdateRequest> updateUser(UserUpdateRequest userUpdateRequest) {
        User user = findUserAllUp(userUpdateRequest.getGraduationYear(), userUpdateRequest.getBranch(), userUpdateRequest.getGraduate(), userUpdateRequest.getWork(), userUpdateRequest.getHobby(), userUpdateRequest.getFirstName(), userUpdateRequest.getLastName(), userUpdateRequest.getMiddleName());
        user.setUserBranches(getUserBranchesFromClient(user, Collections.singletonList(userUpdateRequest.getBranch())));
        user.setUserHobbies(getUserHobbyFromClient(user, Collections.singletonList(userUpdateRequest.getHobby())));
        userRepository.save(user);
        return ResponseEntity.ok(new UserUpdateRequest(userUpdateRequest.getGraduationYear(), userUpdateRequest.getBranch(), userUpdateRequest.getGraduate(), userUpdateRequest.getWork(), userUpdateRequest.getHobby(), userUpdateRequest.getFirstName(), userUpdateRequest.getLastName(), userUpdateRequest.getMiddleName()));
    }

    //
    @Override
    public ResponseEntity<UserCreateRequest> createUser(UserCreateRequest userCreateRequest) {

        User users = findUserAllCreate(userCreateRequest.getEmail(), userCreateRequest.getPassword(), userCreateRequest.getGraduationYear(), userCreateRequest.getBranch(), userCreateRequest.getGraduate(), userCreateRequest.getWork(), userCreateRequest.getHobby(), userCreateRequest.getFirstName(), userCreateRequest.getLastName(), userCreateRequest.getMiddleName());
//        User user = new User();

        users.setUserBranches(getUserBranchesFromClient(users, userCreateRequest.getBranch()));
        users.setUserHobbies(getUserHobbyFromClient(users, Collections.singletonList(userCreateRequest.getHobby())));
        users.setLastName(userCreateRequest.getLastName());
        users.setFirstName(userCreateRequest.getFirstName());
        users.setMiddleName(userCreateRequest.getMiddleName());
        users.setWork(userCreateRequest.getWork());
        users.setEmail(userCreateRequest.getEmail());
        users.setPassword(userCreateRequest.getPassword());
        users.setGraduationYear(userCreateRequest.getGraduationYear());
        users.setGraduate(Graduate.викладач);
        userRepository.save(users);
        return ResponseEntity.ok(new UserCreateRequest(userCreateRequest.getEmail(), userCreateRequest.getPassword(), userCreateRequest.getGraduationYear(), userCreateRequest.getBranch(), userCreateRequest.getGraduate(), userCreateRequest.getWork(), userCreateRequest.getHobby(), userCreateRequest.getFirstName(), userCreateRequest.getLastName(), userCreateRequest.getMiddleName()));

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

    private User findUserAllUp(
            Integer graduationYear, String graduate, String work, String hobby,
            @NotBlank @Pattern(regexp = "^[А-Яа-я]*$") String firstName,
            @NotBlank @Pattern(regexp = "^[А-Яа-я]*$") String lastName,
            @NotBlank @Pattern(regexp = "^[А-Яа-я]*$") String middleName, @NotBlank @Pattern(regexp = "^[А-Яа-я]*$") String name) {
        return userRepository.findByAllUp(graduationYear, graduate, work, hobby, firstName, middleName, lastName).orElseThrow(() -> {
            throw new NotExistsException(USER_IS_NOT_FOUND);
        });
    }

    private User findUserAllCreate(
            @NotBlank @Email String email,
            @NotBlank @Pattern(regexp = "^[A-Za-z1-9]{12,18}$") String password,
            Integer graduationYear, @NotBlank List<String> graduate, String work, String hobby,
            @NotBlank @Pattern(regexp = "^[А-Яа-я]*$") String firstName,
            @NotBlank @Pattern(regexp = "^[А-Яа-я]*$") String lastName,
            @NotBlank @Pattern(regexp = "^[А-Яа-я]*$") String middleName, @NotBlank @Pattern(regexp = "^[А-Яа-я]*$") String name) {
        return userRepository.findByAllCreate(email, password, graduationYear, graduate, work, hobby, firstName, middleName, lastName).orElseThrow(() -> {
            throw new NotExistsException(USER_IS_NOT_FOUND);
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
