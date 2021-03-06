package com.lpnu.poly.DTO.users;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;


@Getter
@Setter
public class UserUpdateDTO {

    @Email
    private String email;

    @NotEmpty
    @Min(1960)
    @Max(2300)
    private Integer graduationYear;

    private String  work;

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
