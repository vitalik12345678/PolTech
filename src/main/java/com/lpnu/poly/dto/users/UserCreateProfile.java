package com.lpnu.poly.dto.users;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class UserCreateProfile {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z1-9]{12,18}$")
    private String password;

    @NotEmpty
    @Min(1960)
    @Max(2300)
    private Integer graduationYear;

    @NotBlank
    private List<String> branch;

//    @NotBlank
//    private String graduate;

    private String work;

//    @NotBlank
//    private String hobby;

    @NotBlank
    @Pattern(regexp = "^[А-Яа-я]*$")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[А-Яа-я]*$")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[А-Яа-я]*$")
    private String middleName;

}
//{
//        "email":"dsfsfsdf7@gmail.com",
//        "password":"123asdasdasdg",
//        "graduationYear":"2000",
//        "work":"Lector",
//        "firstName" : "test",
//        "lastName" : "test",
//        "middleName" : "test"
//}