package com.lpnu.poly.DTO.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class JWTResponse {

    private String jwt;
    private List<String> roles;

}
