package com.lpnu.poly.DTO.post;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
public class PostCreateRequest {

    private String title;
    private String photoURI;
    private List<String> branch;
    private List<String> hobby;

}
