package com.resume_management.resume_management_back.user.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resume_management.resume_management_back.user.dto.JoinRequestDTO;
import com.resume_management.resume_management_back.user.dto.JoinResponseDTO;
import com.resume_management.resume_management_back.user.dto.LoginRequestDTO;
import com.resume_management.resume_management_back.user.dto.LoginResponseDTO;
import com.resume_management.resume_management_back.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    // 회원가입 & 로그인
    @PostMapping("/join")
    public ResponseEntity<JoinResponseDTO> join(@RequestBody JoinRequestDTO joinRequestDTO) {
        JoinResponseDTO result = userService.join(joinRequestDTO);

        if (!result.getErrMsg().isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> submit(@RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO result = userService.login(loginRequest);
        return new ResponseEntity<LoginResponseDTO>(result, HttpStatus.OK);
    }
    
    
}
