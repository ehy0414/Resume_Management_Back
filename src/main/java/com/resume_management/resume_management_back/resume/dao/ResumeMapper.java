package com.resume_management.resume_management_back.resume.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.resume_management.resume_management_back.resume.domain.ResumeResponseDTO;

@Mapper
public interface ResumeMapper {
    
    public List<ResumeResponseDTO> findAllRow();
}
