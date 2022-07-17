package com.lpnu.poly.service.impl;

import com.lpnu.poly.DTO.security.JWTResponse;
import com.lpnu.poly.DTO.security.LoginRequest;
import com.lpnu.poly.DTO.users.UserCreateDTO;
import com.lpnu.poly.DTO.users.UserCurrentDTO;
import com.lpnu.poly.DTO.users.UserProfileDTO;
import com.lpnu.poly.DTO.users.UserUpdateDTO;
import com.lpnu.poly.JWT.JWTUtils;
import com.lpnu.poly.entity.*;
import com.lpnu.poly.entity.mapper.DTOConvertor;
import com.lpnu.poly.exception.ExistsException;
import com.lpnu.poly.exception.NotExistsException;
import com.lpnu.poly.repository.*;
import com.lpnu.poly.security.UserDetailsImpl;
import com.lpnu.poly.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private static final String USER_IS_NOT_EXIST = "User not exist";
    private static final String BRANCH_NOT_EXIST = "Branch doesn't exist";
    private static final String HOBBY_NOT_EXIST = "Hobby doesn't exist";
    private static final String USER_EXIST = "User exist";
    private static final String ROLE_NOT_EXIST = "User doesn't exist";
    private static final String ROLE_USER = "user";
    private final BranchRepository branchRepository;
    private final UserRepository userRepository;
    private final DTOConvertor dtoConvertor;
    private final HobbyRepository hobbyRepository;
    private final UserBranchRepository userBranchRepository;
    private final UserHobbyRepository userHobbyRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder encoder;


    @Autowired
    public UserServiceImpl(BranchRepository branchRepository, UserRepository usersRepository, DTOConvertor dtoConvertor, HobbyRepository hobbyRepository, UserBranchRepository userBranchRepository, UserHobbyRepository userHobbyRepository, RoleRepository roleRepository, AuthenticationManager authenticationManager, JWTUtils jwtUtils, PasswordEncoder encoder) {
        this.branchRepository = branchRepository;
        this.userRepository = usersRepository;
        this.dtoConvertor = dtoConvertor;
        this.hobbyRepository = hobbyRepository;
        this.userBranchRepository = userBranchRepository;
        this.userHobbyRepository = userHobbyRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }


    @Override
    public UserProfileDTO getUser(Long id) {
        return dtoConvertor.convertToDTO(findUser(id), new UserProfileDTO());
    }

    @Override
    public UserProfileDTO deleteUser(Long id) {
        User user = findUser(id);
        userRepository.delete(user);
        return dtoConvertor.convertToDTO(user, new UserProfileDTO());
    }

    @Override
    public UserProfileDTO updateUser(UserUpdateDTO userUpdateDTO) {
        User user = findUser(userUpdateDTO.getEmail());

        user.setUserBranches(getUserBranchesFromClient(user, userUpdateDTO.getBranch()));
        user.setUserHobbies(getUserHobbyFromClient(user, userUpdateDTO.getHobby()));

        userHobbyRepository.deleteAll(userHobbyRepository.findByUser(user));
        userBranchRepository.deleteAll(userBranchRepository.findByUser(user));

        userBranchRepository.saveAll(getUserBranchesFromClient(user, userUpdateDTO.getBranch()));
        userHobbyRepository.saveAll(getUserHobbyFromClient(user, userUpdateDTO.getHobby()));

        userRepository.save(user);

        return dtoConvertor.convertToDTO(user, new UserProfileDTO());
    }


    @Override
    public UserProfileDTO createUser(UserCreateDTO userCreateDTO) {

        Optional<User> optionalUser = userRepository.findByEmail(userCreateDTO.getEmail());

        if (optionalUser.isPresent()) {
            throw new ExistsException(USER_EXIST);
        }

        User user = dtoConvertor.convertToEntity(userCreateDTO, new User());
        user.setUserBranches(getUserBranchesFromClient(user, userCreateDTO.getBranch()));
        user.setUserHobbies(getUserHobbyFromClient(user, userCreateDTO.getHobby()));
        user.setPassword(encoder.encode(userCreateDTO.getPassword()));
        user.setGraduate(Graduate.valueOf(userCreateDTO.getGraduate().toLowerCase(Locale.ROOT)));
        user.setRole(roleRepository.findByName(ROLE_USER).orElseThrow(() -> {
            throw new NotExistsException(ROLE_NOT_EXIST);
        }));
        userRepository.save(user);
        userHobbyRepository.saveAll(getUserHobbyFromClient(user, userCreateDTO.getHobby()));
        userBranchRepository.saveAll(getUserBranchesFromClient(user, userCreateDTO.getBranch()));
        return dtoConvertor.convertToDTO(user, new UserProfileDTO());
    }

    @Override
    public UserCurrentDTO getCurrentUser() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = findUser(userDetails.getUsername());

        UserCurrentDTO response = dtoConvertor.convertToDTO(user, new UserCurrentDTO());
        response.setRole(String.valueOf(userDetails.getAuthorities().stream().findFirst().orElseThrow(() -> {
            throw new NotExistsException("Role doesn't exist");
        })));
        return response;
    }

    @Override
    public JWTResponse singin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return new JWTResponse(jwt, roles);
    }

    private List<UserHobby> getUserHobbyFromClient(User user, List<String> hobby) {
        return hobby.stream().map(hobbyName -> {
            UserHobby userHobby = new UserHobby();
            userHobby.setUser(user);
            userHobby.setHobby(findHobby(hobbyName));
            return userHobby;
        }).collect(Collectors.toList());
    }

    private List<UserBranch> getUserBranchesFromClient(User user, List<String> branch) {
        return branch.stream().map( branchName -> {
            UserBranch userBranch = new UserBranch();
            userBranch.setUser(user);
            userBranch.setBranch(findBranch(branchName));
            return userBranch;
        }).collect(Collectors.toList());
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new NotExistsException(USER_IS_NOT_EXIST);
        });
    }

    private User findUser(String email) {
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
