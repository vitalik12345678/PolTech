package com.lpnu.poly.DTO.post;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostUpdateRequest {

    private String title;
    private List<String> branch;
    private List<String> hobby;

}
