package com.lpnu.poly.dto.users;

import javax.validation.constraints.*;

public class UsersUpdateProfile {

    @NotEmpty
    @Min(1960)
    @Max(2300)
    private Integer graduationYear;

    @NotBlank
    private String branch;

    @NotBlank
    private String graduate;

    private String work;

    @NotBlank
    private String hobby;

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
