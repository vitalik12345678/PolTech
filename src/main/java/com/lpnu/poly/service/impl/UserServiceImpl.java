package com.lpnu.poly.service.impl;

import com.lpnu.poly.dto.users.UserCreateRequest;
import com.lpnu.poly.dto.users.UserProfileResponse;
import com.lpnu.poly.dto.users.UserUpdateRequest;
import com.lpnu.poly.entity.Branch;
import com.lpnu.poly.entity.User;
import com.lpnu.poly.entity.UserBranch;
import com.lpnu.poly.exception.NotExistException;
import com.lpnu.poly.repository.BranchRepository;
import com.lpnu.poly.repository.UsersRepository;
import com.lpnu.poly.service.UserService;
import com.lpnu.poly.entity.Graduate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final UsersRepository userRepository;
    private final String USER_IS_NOT_EXIST="User not exist";
    private final String USER_IS_NOT_FOUND="User wasn't found";


    @Autowired
    public UserServiceImpl(BranchRepository branchRepository, UsersRepository usersRepository) {
        this.branchRepository = branchRepository;
        this.userRepository = usersRepository;
    }


    @Override
    public ResponseEntity<List<UserProfileResponse>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserProfileResponse> responseList = new ArrayList<>();
        users.forEach(user ->{
            UserProfileResponse userProfileResponse = new UserProfileResponse();
            BeanUtils.copyProperties(user, userProfileResponse);
        });
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<UserProfileResponse> findUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(new UserProfileResponse());
        } else {
            log.error(USER_IS_NOT_FOUND);
            throw new NotExistException(USER_IS_NOT_EXIST);
        }
    }


    @Override
    public ResponseEntity<UserProfileResponse> deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return ResponseEntity.ok(new UserProfileResponse());
        } else {
            log.error(USER_IS_NOT_FOUND);
            throw new NotExistException(USER_IS_NOT_EXIST);
        }
    }

    @Override
    public ResponseEntity<UserUpdateRequest> updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            BeanUtils.copyProperties(userUpdateRequest, user);
            userRepository.save(user);
            return new ResponseEntity<>(new UserUpdateRequest(),HttpStatus.OK);
        } else {
            log.error(USER_IS_NOT_FOUND);
            throw new NotExistException(USER_IS_NOT_EXIST);
        }
    }

    @Override
    public ResponseEntity<UserCreateRequest> createUser(UserCreateRequest userCreateRequest) {
            User user = new User();
            List<Branch> listBranches = new ArrayList<>();
            userCreateRequest.getBranch().forEach(branches ->{
                listBranches.add(branchRepository.findByName(branches).get());
            });

            List<UserBranch> userBranches = new ArrayList<>();

            listBranches.forEach( x -> {
                UserBranch userBranch = new UserBranch();
                userBranch.setUser(user);
                userBranch.setBranch(x);
                userBranches.add(userBranch);
            });

            user.setLastName(userCreateRequest.getLastName());
            user.setFirstName(userCreateRequest.getFirstName());
            user.setMiddleName(userCreateRequest.getMiddleName());
            user.setWork(userCreateRequest.getWork());
            user.setEmail(userCreateRequest.getEmail());
            user.setPassword(userCreateRequest.getPassword());
            user.setGraduationYear(userCreateRequest.getGraduationYear());
            user.setGraduate(Graduate.викладач);
            user.setUserBranches(userBranches);
            userRepository.save(user);
            return new ResponseEntity<>(new UserCreateRequest(), HttpStatus.CREATED);
    }
}
