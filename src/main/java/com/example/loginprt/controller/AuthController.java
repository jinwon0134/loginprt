package com.example.loginprt.controller;

import com.example.loginprt.dto.SignupRequestDTO;
import com.example.loginprt.models.User;
import com.example.loginprt.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@Valid @RequestBody SignupRequestDTO request) {
        User user = userService.registerUser(request);
        return ResponseEntity.ok("회원가입 성공: " + user.getEmail());
    }
}
