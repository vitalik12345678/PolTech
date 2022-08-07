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
    private Boolean commentsAvailable;
    private LocalDateTime publishedDate;
    private Long userId;
    private String avatarURI;
    private Long id;
}
