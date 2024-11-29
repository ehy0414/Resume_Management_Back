package com.resume_management.resume_management_back.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.resume_management.resume_management_back.user.dto.JoinRequestDTO;

@Mapper
public interface UserMapper {
    
    public void join(JoinRequestDTO joinDTO);

    public Boolean existsByEmail(String email);
}
