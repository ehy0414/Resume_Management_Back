package com.resume_management.resume_management_back.user.dto.career;

import lombok.Data;

@Data
public class CareerResponseDTO {
    private int userId;
    private String occupation;
    private String company;
    private String period;
    private String details;
}
