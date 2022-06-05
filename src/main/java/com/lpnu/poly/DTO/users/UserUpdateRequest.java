package com.lpnu.poly.DTO.users;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;


@Getter
@Setter
public class UserUpdateRequest {


    @Email
    private String email;

    @NotEmpty
    @Min(1960)
    @Max(2300)
    private Integer graduationYear;

    @NotNull
    private List<String> branch;

    @NotBlank
    private String graduate;

    @NotBlank
    private String  work;

    @NotNull
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

    public UserUpdateRequest(Integer graduationYear, String branch, String graduate, String work, String hobby, String firstName, String lastName, String middleName) {
    }
}
