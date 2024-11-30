package com.resume_management.resume_management_back.resume.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resume_management.resume_management_back.resume.dao.ResumeMapper;
import com.resume_management.resume_management_back.resume.domain.ResumeResponseDTO;

@Service
public class ResumeService {
    
    @Autowired
    private ResumeMapper resumeMapper;

    public List<ResumeResponseDTO> findAll() {
        System.out.println("debug >>> service findAll " + resumeMapper);
        return resumeMapper.findAllRow();
    }
}
