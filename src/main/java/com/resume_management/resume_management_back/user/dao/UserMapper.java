package com.resume_management.resume_management_back.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.resume_management.resume_management_back.user.dto.JoinRequestDTO;
import com.resume_management.resume_management_back.user.dto.LoginRequestDTO;
import com.resume_management.resume_management_back.user.dto.LoginResponseDTO;

@Mapper
public interface UserMapper {
    
    public void join(JoinRequestDTO joinDTO);

    public Boolean existsByEmail(String email);

    public LoginResponseDTO loginRow(LoginRequestDTO params); 

    public LoginResponseDTO getAllInfo(Integer user_id); 
    public void updateAllInfo(LoginRequestDTO params); 
}
