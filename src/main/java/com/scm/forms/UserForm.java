package com.scm.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "name cannot be blank.")
    @Size(min = 1, message = "minimum 3 charecters required ")
    private String name;

    @NotBlank(message = "Email cannot be blank.")
    private String email;

    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 1, message = "Password length should be min 8")
    private String password;

    @NotBlank(message = "Phonenumber cannot be blank.")
    @Size(min = 1, max = 13, message = "Invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "About cannot be blank.")
    private String about;

}
