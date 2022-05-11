package com.lpnu.poly.service.impl;

import com.lpnu.poly.dto.users.UserCreateProfile;
import com.lpnu.poly.dto.users.UserGetProfile;
import com.lpnu.poly.dto.users.UsersUpdateProfile;
import com.lpnu.poly.entity.Branch;
import com.lpnu.poly.entity.User;
import com.lpnu.poly.exception.NotExistException;
import com.lpnu.poly.repository.BranchRepository;
import com.lpnu.poly.repository.UsersRepository;
import com.lpnu.poly.service.UsersService;
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
public class UsersServiceImpl implements UsersService {

    private final BranchRepository branchRepository;
    private final UsersRepository userRepository;
    private final String USER_IS_NOT_EXIST="User not exist";
    private final String USER_IS_NOT_FOUND="User wasn't found";


    @Autowired
    public UsersServiceImpl(BranchRepository branchRepository, UsersRepository usersRepository) {
        this.branchRepository = branchRepository;
        this.userRepository = usersRepository;
    }


    @Override
    public ResponseEntity<List<UserGetProfile>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserGetProfile> responseList = new ArrayList<>();
        users.forEach(user ->{
            UserGetProfile userGetProfile = new UserGetProfile();
            BeanUtils.copyProperties(user, userGetProfile);
        });
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<UserGetProfile> findUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            log.error(USER_IS_NOT_FOUND);
            throw new NotExistException(USER_IS_NOT_EXIST);
        }
    }


    @Override
    public ResponseEntity<UserGetProfile> deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return ResponseEntity.ok(optionalUser.get());
        } else {
            log.error(USER_IS_NOT_FOUND);
            throw new NotExistException(USER_IS_NOT_EXIST);
        }
    }

    @Override
    public ResponseEntity<UsersUpdateProfile> updateUser(Long id, UsersUpdateProfile usersUpdateProfile) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            BeanUtils.copyProperties(usersUpdateProfile, user);
            userRepository.save(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        } else {
            log.error(USER_IS_NOT_FOUND);
            throw new NotExistException(USER_IS_NOT_EXIST);
        }
    }

    @Override
    public ResponseEntity<UserCreateProfile> createUser(UserCreateProfile userCreateProfile) {
            User user = new User();
            List<Branch> listBranches = new ArrayList<>();
            userCreateProfile.getBranch().forEach(branches ->{
                listBranches.add(branchRepository.findByName(branches).get());
            });
            user.setLastName(userCreateProfile.getLastName());
            user.setFirstName(userCreateProfile.getFirstName());
            user.setMiddleName(userCreateProfile.getMiddleName());
            user.setWork(userCreateProfile.getWork());
            user.setEmail(userCreateProfile.getEmail());
            user.setPassword(userCreateProfile.getPassword());
            user.setGraduationYear(userCreateProfile.getGraduationYear());
            user.setUserBranches(listBranches);
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
