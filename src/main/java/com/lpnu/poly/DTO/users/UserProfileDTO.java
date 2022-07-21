package com.lpnu.poly.DTO.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDTO {

    private Long id;

    private String email;

    private Integer graduationYear;

    private String graduate;

    private String work;

    private String avatar;

    private String firstName;

    private String lastName;

    private String middleName;

}
