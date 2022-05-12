package com.lpnu.poly.DTO.post;

import com.lpnu.poly.entity.mapper.Convertable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostProfileResponse implements Convertable {

    private String title;
}
