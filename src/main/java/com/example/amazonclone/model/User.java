package com.example.amazonclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class User {
    @NotEmpty
    @Size(min=3, message ="name must be longer than 2 characters")
    private String ID;
    @NotEmpty
    @Size(min=5, max=10, message ="name at least have to be 5 characters long")
    private String username;
    @NotEmpty(message = "password can't be empty")
    @Size(min = 6,max = 15,message = "password must be more than 5 char and less than 15 char")
   // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",message = "please enter strong password")
    private String password;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty(message = "role can't be empty")
    @Pattern(regexp = "(Admin|Customer)",message = "Role must be  Admin or Customer only")
    private String role;
    @NotNull
    @PositiveOrZero(message = "can't be negative")
    private int balance;
}
