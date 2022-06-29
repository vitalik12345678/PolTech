package com.lpnu.poly.DTO.post;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class PostCreateDTO {

    @NotBlank
    @Length(max = 80,message = "Post title had more than 80 symbols")
    private String title;
    @NotBlank
    private String context;
    @NotEmpty(message = "Post branch/branches are empty")
    private List<String> branch;
    @NotEmpty(message = "Post hobby/hobbies are empty")
    private List<String> hobby;

}
