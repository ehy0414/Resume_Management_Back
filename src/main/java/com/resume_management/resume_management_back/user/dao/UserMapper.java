package com.resume_management.resume_management_back.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.resume_management.resume_management_back.user.dto.JoinRequestDTO;
import com.resume_management.resume_management_back.user.dto.LoginRequestDTO;
import com.resume_management.resume_management_back.user.dto.LoginResponseDTO;
import com.resume_management.resume_management_back.user.dto.career.CareerResponseDTO;

@Mapper
public interface UserMapper {
    
    public void join(JoinRequestDTO joinDTO);

    public Boolean existsByEmail(String email);

    public LoginResponseDTO loginRow(LoginRequestDTO params); 

    public LoginResponseDTO getAllInfo(Integer user_id); 
    public void updateAllInfo(LoginRequestDTO params); 

    public List<CareerResponseDTO> getCareer(int userId); 

    public void deleteCareer(int userId);

    public void inputCareer(CareerResponseDTO params); 
}
