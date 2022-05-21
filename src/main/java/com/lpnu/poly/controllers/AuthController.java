package com.lpnu.poly.controllers;

import com.lpnu.poly.DTO.security.JWTResponse;
import com.lpnu.poly.DTO.security.LoginRequest;
import com.lpnu.poly.DTO.security.SignupRequest;
import com.lpnu.poly.DTO.users.UserCreateRequest;
import com.lpnu.poly.JWT.JWTUtils;
import com.lpnu.poly.entity.Graduate;
import com.lpnu.poly.entity.Role;
import com.lpnu.poly.entity.User;
import com.lpnu.poly.exception.NotExistsException;
import com.lpnu.poly.repository.RoleRepository;
import com.lpnu.poly.repository.UserRepository;
import com.lpnu.poly.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JWTUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JWTResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles));
     /*   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        User user = (principal instanceof User) ? (User) principal : null;
            UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String jwt = jwtUtils.generateJwtToken(auth);
        return ResponseEntity.ok(jwt);*/
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserCreateRequest signUpRequest) {

       // userRepository.findByEmail(signUpRequest.getEmail()).get();


        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setRole(roleRepository.findByName("user").orElseThrow( () -> {
            throw new NotExistsException("User doesn't exist");
        }));
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setMiddleName(signUpRequest.getMiddleName());
        user.setGraduate(Graduate.valueOf(signUpRequest.getGraduate()));
        user.setGraduationYear(signUpRequest.getGraduationYear());
        userRepository.save(user);
        return ResponseEntity.ok(new RuntimeException("User registered successfully!"));
    }

}
