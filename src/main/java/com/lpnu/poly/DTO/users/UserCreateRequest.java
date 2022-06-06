package com.lpnu.poly.DTO.users;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Setter
@Getter
public class UserCreateRequest {
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

    @NotBlank
    private String graduate;

    private String work;

    @NotBlank
    private List<String> hobby;

    @NotBlank
    @Pattern(regexp = "^[А-Яа-я]*$")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[А-Яа-я]*$")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^[А-Яа-я]*$")
    private String middleName;

    public UserCreateRequest(String email, String password, Integer graduationYear, List<String> branch, String graduate, String work, String hobby, String firstName, String lastName, String middleName) {
    }
}
//{
/*
        "email":"dsfsfsdf7@gmail.com",
        "password":"123asdasdasdg",
        "graduationYear":"2000",
        "work":"Lector",
        "firstName" : "test",
        "lastName" : "test",
        "middleName" : "test"
*/
//}