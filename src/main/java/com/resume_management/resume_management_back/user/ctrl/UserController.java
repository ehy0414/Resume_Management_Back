package com.resume_management.resume_management_back.user.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.resume_management.resume_management_back.user.dto.JoinRequestDTO;
import com.resume_management.resume_management_back.user.dto.JoinResponseDTO;
import com.resume_management.resume_management_back.user.dto.LoginRequestDTO;
import com.resume_management.resume_management_back.user.dto.LoginResponseDTO;
import com.resume_management.resume_management_back.user.dto.career.CareerResponseDTO;
import com.resume_management.resume_management_back.user.service.UserService;

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
        System.out.println(result);
        return new ResponseEntity<LoginResponseDTO>(result, HttpStatus.OK);
    }

    @GetMapping("/getAllInfo/{user_id}")
    public ResponseEntity<LoginResponseDTO> getAllInfo(@PathVariable("user_id") Integer user_id) {
        LoginResponseDTO result = userService.getAllInfo(user_id);
        return new ResponseEntity<LoginResponseDTO>(result, HttpStatus.OK);
    }
    
    // @PostMapping("/updateAllInfo")
    // public ResponseEntity<Void> updateAllInfo(@RequestBody LoginRequestDTO params) {
    //     userService.updateAllInfo(params);
    //     return new ResponseEntity<Void>(HttpStatus.OK);
    // }
    

     @PostMapping("/updateAllInfo")
    public ResponseEntity<Void> updateAllInfo(
            @RequestParam("userId") int userId,
            @RequestParam("name") String name,
            @RequestParam("desiredJob") String desiredJob,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("github") String github,
            @RequestParam("skill") String skill,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("profileImage") String profileImage,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        // DTO 생성 및 서비스 호출을 위해 정보를 설정
        LoginRequestDTO params = new LoginRequestDTO();
        params.setUserId(userId);
        params.setName(name);
        params.setDesiredJob(desiredJob);
        params.setPhone(phone);
        params.setAddress(address);
        params.setGithub(github);
        params.setSkill(skill);
        params.setTitle(title);
        params.setContent(content);
        
     
        // 이미지 파일 처리 (업로드 로직 추가)
        if (image != null && !image.isEmpty()) {
            userService.saveImage(image, userId);
            params.setProfileImage(userId+"_"+image.getOriginalFilename() ); 
        }else{
            params.setProfileImage(profileImage);
        }
        System.out.println(params);
        // 서비스 호출
        userService.updateAllInfo(params);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/getCareer/{userId}")
    public ResponseEntity<List<CareerResponseDTO>> getCareer(@PathVariable("userId") int userId) {
        List<CareerResponseDTO> result = userService.getCareer(userId);
        return new ResponseEntity<List<CareerResponseDTO>>(result,HttpStatus.OK);
    }
    
    @PostMapping("/updateCareer")
    public ResponseEntity<Void> updateCareer(@RequestBody List<CareerResponseDTO> list) {
        userService.updateCareer(list);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
}
