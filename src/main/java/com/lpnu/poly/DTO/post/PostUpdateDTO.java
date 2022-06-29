package com.lpnu.poly.DTO.post;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class PostUpdateDTO {

    @NotBlank
    @Length(max = 80,message = "Post title had more than 80 symbols")
    private String title;
    @NotEmpty(message = "Post branch/branches are empty")
    private List<String> branch;
    @NotEmpty(message = "Post hobby/hobbies are empty")
    private List<String> hobby;

}
