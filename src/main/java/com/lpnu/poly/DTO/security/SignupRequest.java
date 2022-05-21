package com.lpnu.poly.DTO.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    private String email;
    private String password;
    private String role;

}
