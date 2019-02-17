package com.divine.logging.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @NotBlank(message = "username must'n be empty")
    private String username;
    @NotBlank(message = "password must'n be empty")
    private String password;
    @Min(value = 1, message = "age must be great than 1")
    @Max(value = 150, message = "age must be less than 150")
    private int age;
}
