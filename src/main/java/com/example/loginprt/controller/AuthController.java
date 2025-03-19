package com.example.loginprt.controller;

import com.example.loginprt.dto.LoginRequestDTO;
import com.example.loginprt.dto.SignupRequestDTO;
import com.example.loginprt.models.User;
import com.example.loginprt.service.AuthService;
import com.example.loginprt.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;
    private final HttpServletRequest httpServletRequest;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@Valid @RequestBody SignupRequestDTO request) {
        User user = userService.registerUser(request);
        return ResponseEntity.ok("회원가입 성공: " + user.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO request) {
        boolean isAuthenticated = authService.login(request, httpServletRequest);

        if (isAuthenticated) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(401).body("로그인 실패: 이메일 또는 비밀번호가 다릅니다.");
        }
    }
    @GetMapping("/session")
    public ResponseEntity<String> getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // 현재 세션이 있으면 가져오고, 없으면 null 반환

        if (session != null) {
            String userEmail = (String) session.getAttribute("userEmail");
            if (userEmail != null) {
                return ResponseEntity.ok("현재 로그인한 사용자: " + userEmail);
            }
        }

        return ResponseEntity.status(401).body("로그인되지 않은 상태입니다.");
    }

    // 🔹 로그아웃 (세션 무효화)
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // 세션 무효화 (로그아웃)
        }
        return ResponseEntity.ok("로그아웃 성공");
    }


}
