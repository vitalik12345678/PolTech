package com.lpnu.poly.DTO.users;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileResponse {

        private Integer graduationYear;

        private String graduate;

        private String work;

        private String firstName;

        private String lastName;

        private String middleName;

}
