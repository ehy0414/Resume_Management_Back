package com.resume_management.resume_management_back.resume.domain;

import lombok.Data;

@Data
public class ResumeResponseDTO {
    private Integer user_id;
    private String email;
    private String name;
}
