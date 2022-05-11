package com.lpnu.poly.dto.users;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserGetProfile {

        private Integer graduationYear;

//    private String branch;

//    private String graduate;

        private String work;

//    private String hobby;

        private String firstName;

        private String lastName;

        private String middleName;

}
