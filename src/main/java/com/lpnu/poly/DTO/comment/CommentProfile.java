package com.lpnu.poly.DTO.comment;

import com.lpnu.poly.entity.mapper.Convertable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentProfile implements Convertable {

    private Long id;
    private String description;

}
