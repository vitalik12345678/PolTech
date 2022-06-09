package com.lpnu.poly.DTO.comment;

import com.lpnu.poly.entity.mapper.Convertable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentCreateRequest implements Convertable {

    @NotBlank
    private String context;
    @NotNull
    private Long userId;
    @NotNull
    private Long postId;


}
