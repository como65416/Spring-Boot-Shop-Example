package com.comoco.demoshop.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterRequest {
    @Length(min = 4, max = 20)
    private String username;

    @Length(min = 8, max = 30)
    private String password;

    @NotBlank(message = "Name is required")
    private String name;

    @Email
    private String email;
}
