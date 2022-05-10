package com.lpnu.poly.service.impl;

import com.lpnu.poly.dto.users.UserCreateProfile;
import com.lpnu.poly.dto.users.UsersUpdateProfile;
import com.lpnu.poly.entity.User;
import com.lpnu.poly.exception.NotExistException;
import com.lpnu.poly.repository.UsersRepository;
import com.lpnu.poly.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> players = usersRepository.findAll();
        return ResponseEntity.ok(players);
    }

    @Override
    public ResponseEntity<User> findUser(Long id) {
        Optional<User> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            log.error("Player wasnt found");
            throw new NotExistException("Player not exist");
        }
    }


    @Override
    public ResponseEntity<User> deleteUser(Long id) {
        Optional<User> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            usersRepository.delete(optionalUser.get());
            return ResponseEntity.ok(optionalUser.get());
        } else {
            log.error("Player wasnt found");
            throw new NotExistException("Player not exist");
        }
    }

    @Override
    public ResponseEntity<User> updateUser(Long id, UsersUpdateProfile usersUpdateProfile) {
        Optional<User> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            User users = optionalUser.get();
            BeanUtils.copyProperties(usersUpdateProfile, users);
            usersRepository.save(users);
            return new ResponseEntity<>(users,HttpStatus.OK);
        } else {
            log.error("Player wasnt found");
            throw new NotExistException("Player not exist");
        }
    }

    @Override
    public ResponseEntity<User> createUser(UserCreateProfile userCreateProfile) {
            User user = new User();
            user.setLastName(userCreateProfile.getLastName());
            user.setFirstName(userCreateProfile.getFirstName());
            user.setMiddleName(userCreateProfile.getMiddleName());
            user.setWork(userCreateProfile.getWork());
            user.setEmail(userCreateProfile.getEmail());
            user.setPassword(userCreateProfile.getPassword());
            user.setGraduationYear(userCreateProfile.getGraduationYear());
            usersRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
