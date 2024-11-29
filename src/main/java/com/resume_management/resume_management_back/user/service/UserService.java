package com.resume_management.resume_management_back.user.service;

import org.springframework.stereotype.Service;

import com.resume_management.resume_management_back.user.dao.UserMapper;
import com.resume_management.resume_management_back.user.dto.JoinRequestDTO;
import com.resume_management.resume_management_back.user.dto.JoinResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public Boolean existsByEmail(String email) {
        return userMapper.existsByEmail(email);
    }

    public JoinResponseDTO join(JoinRequestDTO joinDTO) {
        if (userMapper.existsByEmail(joinDTO.getEmail())) {
            return new JoinResponseDTO("동일한 이메일을 사용하는 계정이 이미 존재합니다.");
        }

        joinDTO.setPassword(passwordEncoder.encode(joinDTO.getPassword()));
        // joinDTO.setRole("ROLE_BABONE");

        userMapper.join(joinDTO);

        return new JoinResponseDTO("");
    }
}
