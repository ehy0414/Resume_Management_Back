package com.resume_management.resume_management_back.resume.domain;

import lombok.Data;

@Data
public class ResumeResponseDTO {
    private Integer userId;
    private String email;
    private String name;
    private String skill;
    private String profile_image;
    private String content;

}
