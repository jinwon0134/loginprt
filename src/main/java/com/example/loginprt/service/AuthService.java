package com.example.loginprt.service;

import com.example.loginprt.dto.LoginRequestDTO;
import com.example.loginprt.models.User;
import com.example.loginprt.repostitory.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public boolean login(LoginRequestDTO request, HttpServletRequest httpRequest) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // 비밀번호 검증
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                // ✅ 세션 생성 및 사용자 정보 저장 (이메일만 저장)
                HttpSession session = httpRequest.getSession(true);  // 세션 생성
                session.setAttribute("userEmail", user.getEmail());  // 세션에 이메일 저장 (간단한 데이터만)

                return true;
            }
        }

        return false;
    }
}
