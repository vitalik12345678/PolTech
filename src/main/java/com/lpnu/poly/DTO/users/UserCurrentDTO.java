package com.lpnu.poly.DTO.users;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserCurrentDTO {

    private String lastName;
    private String middleName;
    private String firstName;
    private String avatar;
    private String work;
    private String email;
    private Integer graduationYear;
    private String graduate;
    private String role;
    private List<String> hobby;
    private List<String> branch;

}
