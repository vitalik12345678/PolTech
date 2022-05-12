package com.lpnu.poly.DTO.post;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
public class PostCreateRequest {

    @Email
    private String email;
    private String title;
    private List<String> branch;
    private List<String> hobby;

}
