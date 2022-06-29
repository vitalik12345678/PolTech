package com.lpnu.poly.DTO.post;

import com.lpnu.poly.entity.mapper.Convertable;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostProfileDTO implements Convertable {

    private String title;
    private String context;
    private LocalDateTime publishedDate;
    private Long userId;
    private Long fileId;
}
