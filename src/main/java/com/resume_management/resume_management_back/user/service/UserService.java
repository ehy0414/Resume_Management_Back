package com.resume_management.resume_management_back.user.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.resume_management.resume_management_back.user.dao.UserMapper;
import com.resume_management.resume_management_back.user.dto.JoinRequestDTO;
import com.resume_management.resume_management_back.user.dto.JoinResponseDTO;
import com.resume_management.resume_management_back.user.dto.LoginRequestDTO;
import com.resume_management.resume_management_back.user.dto.LoginResponseDTO;
import com.resume_management.resume_management_back.user.dto.career.CareerResponseDTO;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserMapper userMapper;

    @Value("${file.upload-dir}") // application.properties에서 경로를 설정
    private String uploadDir;

    public Boolean existsByEmail(String email) {
        return userMapper.existsByEmail(email);
    }

    public JoinResponseDTO join(JoinRequestDTO joinDTO) {
        if (userMapper.existsByEmail(joinDTO.getEmail())) {
            return new JoinResponseDTO("동일한 이메일을 사용하는 계정이 이미 존재합니다.");
        }

        joinDTO.setPassword(joinDTO.getPassword());
        // joinDTO.setRole("ROLE_BABONE");

        userMapper.join(joinDTO);

        return new JoinResponseDTO("");
    }

    public LoginResponseDTO login(LoginRequestDTO params) {
        return userMapper.loginRow(params);
    }

    public LoginResponseDTO getAllInfo(Integer user_id) {
        return userMapper.getAllInfo(user_id);
    }

    public void updateAllInfo(LoginRequestDTO params) {
        userMapper.updateAllInfo(params);
    }

    public void saveImage(MultipartFile image, int userId) {
        if (image != null && !image.isEmpty()) {
            try {
                // 파일 이름 설정 (현재 시간 + 원래 파일 이름)
                String fileName = userId + "_" + image.getOriginalFilename();
                Path path = Paths.get(uploadDir + fileName);
    
                // 파일이 이미 존재하는지 확인
                if (Files.exists(path)) {
                    // 파일이 존재하면 삭제
                    Files.delete(path);
                }
    
                // 파일 저장
                Files.copy(image.getInputStream(), path);
                // 파일이 저장된 후 필요한 후처리 (예: DB에 파일 경로 저장 등)
            } catch (IOException e) {
                e.printStackTrace(); // 예외 처리
                throw new RuntimeException("파일 저장 실패: " + e.getMessage());
            }
        }
    }
    
    public List<CareerResponseDTO> getCareer(int userId){
        return userMapper.getCareer(userId);
    }

    public void updateCareer(List<CareerResponseDTO> list) {
        if (list == null || list.isEmpty()) {
            return; // 리스트가 비어있으면 아무 작업도 하지 않음
        }
    
        int userId = list.get(0).getUserId(); // 첫 번째 요소에서 userId 가져오기
        
        // 트랜잭션 시작 (예: @Transactional 어노테이션 사용)
        userMapper.deleteCareer(userId); // 해당 사용자의 경력 삭제
        System.out.println(list);
        for (CareerResponseDTO career : list) {
            userMapper.inputCareer(career); // 각 CareerResponseDTO를 삽입
        }
    }
}
