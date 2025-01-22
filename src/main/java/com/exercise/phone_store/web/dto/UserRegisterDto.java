package com.exercise.phone_store.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDto {

    @NotBlank(message = "need to enter email")
    @Email(message = "need to enter valid email")
    private String email;

    @NotNull(message = "need to enter password")
    @Size(min = 5, max = 30, message = "password should be between 5 and 30 characters")
    private String password;
}
