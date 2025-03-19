package com.example.loginprt.dto;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class LoginRequestDTO {
    private String email;
    private String password;
}
